FROM maven:3.9.5-eclipse-temurin-17

# 필수 패키지 설치 + Xvfb 추가
RUN apt-get update && apt-get install -y \
    wget curl unzip gnupg2 software-properties-common \
    firefox xvfb x11-utils \
    fonts-liberation libappindicator3-1 libasound2 libatk-bridge2.0-0 libatk1.0-0 \
    libcups2 libdbus-1-3 libgdk-pixbuf2.0-0 libnspr4 libnss3 libx11-xcb1 libxcomposite1 \
    libxdamage1 libxrandr2 xdg-utils

# Chrome 설치
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable

# ChromeDriver 설치 (버전 고정)
RUN wget https://chromedriver.storage.googleapis.com/114.0.5735.90/chromedriver_linux64.zip \
    && unzip chromedriver_linux64.zip \
    && mv chromedriver /usr/bin/chromedriver \
    && chmod +x /usr/bin/chromedriver

# 프로젝트 복사
WORKDIR /app
COPY . /app

# 테스트 실행 (Xvfb로 디스플레이 설정)
CMD bash -c "Xvfb :99 -screen 0 1920x1080x24 & export DISPLAY=:99 && mvn clean test"