package com.firefighter.aenitto.auth.repository;

import com.firefighter.aenitto.auth.domain.RefreshToken;
import com.firefighter.aenitto.rooms.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository{

    private final EntityManager em;

    @Override
    public RefreshToken saveRefreshToken(RefreshToken refreshToken) {
        em.persist(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken findByMemberId(UUID memberId) {
        return em.createQuery("SELECT r FROM RefreshToken r WHERE r.memberId = :memberId", RefreshToken.class)
                .setParameter("memberId" , memberId)
                .getSingleResult();
    }

    @Override
    public RefreshToken findRefreshTokenById(Long id) {
        return em.find(RefreshToken.class, id);
    }
}
