package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.AuthToken;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AuthTokenRepository {

    private final EntityManager em;

    public AuthTokenRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<AuthToken> findValidToken(String token, LocalDateTime now) {
        return em.createQuery("""
                FROM AuthToken t
                WHERE t.token = :token
                  AND t.revokedAt IS NULL
                  AND t.expiresAt > :now
                """, AuthToken.class)
                .setParameter("token", token)
                .setParameter("now", now)
                .getResultStream()
                .findFirst();
    }

    @Transactional
    public void save(AuthToken authToken) {
        if (authToken.getId() == null) {
            em.persist(authToken);
        } else {
            em.merge(authToken);
        }
    }

    @Transactional
    public boolean revoke(String token, LocalDateTime now) {
        int updated = em.createQuery("""
                UPDATE AuthToken t
                SET t.revokedAt = :now
                WHERE t.token = :token
                  AND t.revokedAt IS NULL
                """)
                .setParameter("token", token)
                .setParameter("now", now)
                .executeUpdate();

        return updated > 0;
    }
}
