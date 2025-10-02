FROM maven:3.9.5-eclipse-temurin-17

# 필수 패키지 설치 + Xvfb 추가
RUN apt-get update && apt-get install -y \
    wget curl unzip gnupg2 software-properties-common \
    firefox xvfb x11-utils \
    fonts-liberation libappindicator3-1 libasound2 libatk-bridge2.0-0 libatk1.0-0 \
    libcups2 libdbus-1-3 libgdk-pixbuf2.0-0 libnspr4 libnss3 libx11-xcb1 libxcomposite1 \
    libxdamage1 libxrandr2 xdg-utils

# 기존 Chrome 제거 (혹시 설치되어 있을 경우)
RUN apt-get remove -y google-chrome-stable || true

# Chrome v139 설치 (사전 업로드된 .deb 파일 사용)
COPY google-chrome-stable_139.0.0.0-1_amd64.deb /tmp/
RUN apt install -y /tmp/google-chrome-stable_139.0.0.0-1_amd64.deb \
    && rm /tmp/google-chrome-stable_139.0.0.0-1_amd64.deb

# ChromeDriver v139 설치
RUN wget https://chromedriver.storage.googleapis.com/139.0.0.0/chromedriver_linux64.zip \
    && unzip chromedriver_linux64.zip \
    && mv chromedriver /usr/bin/chromedriver \
    && chmod +x /usr/bin/chromedriver \
    && rm chromedriver_linux64.zip

# 프로젝트 복사
WORKDIR /app
COPY . /app

# 테스트 실행 (Xvfb로 디스플레이 설정)
CMD ["bash", "-c", "Xvfb :99 -screen 0 1920x1080x24 & export DISPLAY=:99 && mvn clean test"]