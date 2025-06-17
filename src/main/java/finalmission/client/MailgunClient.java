package finalmission.client;

import finalmission.client.dto.SendEmailRequest;
import finalmission.global.MailgunException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

public class MailgunClient implements MailClient {

    private static final Logger log = LoggerFactory.getLogger(MailgunClient.class);

    private static final String FROM = "postmaster@sandbox493a7c6d7f3b418ba011700537536d77.mailgun.org";

    private final RestClient restClient;

    public MailgunClient(final RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void sendEmail(final SendEmailRequest request) {
        log.info("Mailgun 이메일 전송 API 시작 : {}", request.to());

        restClient.post()
                .uri(builder -> builder
                        .queryParam("from", FROM)
                        .queryParam("to", request.to())
                        .queryParam("subject", request.subject())
                        .queryParam("text", request.content())
                        .build()
                )
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        (req, res) -> {
                            throw new MailgunException(
                                    "예약 성공 메일 전송에 실패했습니다.",
                                    res.getStatusCode().value(),
                                    res.getBody().toString()
                            );
                        })
                .toBodilessEntity();

        log.info("Mailgun 이메일 전송 API 성공");
    }
}
