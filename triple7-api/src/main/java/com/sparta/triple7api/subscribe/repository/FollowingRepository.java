package com.sparta.triple7api.subscribe.repository;

import com.sparta.triple7api.subscribe.entity.Following;
import com.sparta.triple7api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowingRepository extends JpaRepository<Following, Long> {
    List<Following> findAllByFollowerUserId(Long FollowerUserId);
    List<Following> findAllByFollowingUserId(Long FollowingUserId);

}
