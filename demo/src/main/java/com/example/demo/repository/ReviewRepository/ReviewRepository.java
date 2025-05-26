package com.example.demo.repository.ReviewRepository;

import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    // 로그인한 회원(=member)이 쓴 리뷰만 페이징 조회
    Page<Review> findByMemberId(Long memberId, Pageable pageable);
}
