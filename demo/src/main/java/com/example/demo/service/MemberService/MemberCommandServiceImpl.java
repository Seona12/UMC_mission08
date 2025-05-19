package com.example.demo.service.MemberService;

import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.apiPayload.exception.handler.FoodCategoryHandler;
import com.example.demo.converter.MemberConverter;
import com.example.demo.converter.MemberPreferConverter;
import com.example.demo.domain.Member;
import com.example.demo.domain.foodCategory;
import com.example.demo.domain.mapping.MemberPrefer;
import com.example.demo.repository.FoodCategoryRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.web.dto.MemberRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<foodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}