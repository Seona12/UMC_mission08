package com.example.demo.service.MemberMissionService;

import com.example.demo.converter.MemberMissionConverter;
import com.example.demo.domain.enums.MissionStatus;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.repository.MemberMissionRepository;
import com.example.demo.validation.annotation.UserExists;
import com.example.demo.web.dto.ChallengeMissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMissionServiceImpl implements MemberMissionService {

    private final MemberMissionConverter converter;
    private final MemberMissionRepository repo;

    @Override
    @Transactional
    public Long challenge(ChallengeMissionRequest req) {
        MemberMission em = converter.toEntity(req);
        return repo.save(em).getId();
    }
    public Page<MemberMission> getInProgressMissionsPage(
            @UserExists Long memberId,
            int page,
            int size
    ) {
        return repo.findAllByMember_IdAndStatus(
                memberId,
                MissionStatus.CHALLENGING,
                PageRequest.of(page, size, Sort.by("createdAt").ascending())
        );
    }

}