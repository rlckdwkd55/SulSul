package com.sulsulmarket.sulsul.review.dao;

import com.sulsulmarket.sulsul.review.dto.ReviewDTO;
import com.sulsulmarket.sulsul.review.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDao implements ReviewMapper {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public int writeReview(ReviewDTO reviewDTO) {
        return reviewMapper.writeReview(reviewDTO);
    }
}
