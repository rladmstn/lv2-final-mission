package finalmission.auth.service;

import finalmission.auth.dto.LoginRequest;
import finalmission.global.UnauthorizedException;
import finalmission.member.domain.Member;
import finalmission.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider tokenProvider;

    public AuthService(MemberRepository memberRepository, JwtTokenProvider tokenProvider) {
        this.memberRepository = memberRepository;
        this.tokenProvider = tokenProvider;
    }

    public String login(LoginRequest request) {
        Member member = memberRepository.findByEmailAndPassword(request.email(), request.password())
                .orElseThrow(() -> new UnauthorizedException("올바르지 않은 로그인입니다."));

        return tokenProvider.createToken(member.getId(), member.getRole());
    }
}
