ARG APP_INSIGHTS_AGENT_VERSION=2.5.1
ARG PLATFORM=""
FROM hmctspublic.azurecr.io/base/java${PLATFORM}:17-distroless

COPY build/libs/finrem-payment-service.jar /opt/app/
COPY lib/AI-Agent.xml /opt/app/

EXPOSE 9001

CMD ["finrem-payment-service.jar"]
