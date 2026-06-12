FROM gradle:8.7-jdk17

USER root

ENV CLI_URL="https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip" \
    ANDROID_HOME="/root/Android/Sdk" \
    ANDROID_SDK_ROOT="/root/Android/Sdk" \
    ANDROID_VERSION=34 \
    ANDROID_BUILD_TOOLS_VERSION=34.0.0 \
    NDK_VERSION="26.1.10909125"

# Create SDK folder
RUN mkdir -p "$ANDROID_HOME" .android \
    && cd "$ANDROID_HOME" \
    && apt update && apt install -y curl unzip wget \
    && curl -o cli.zip $CLI_URL \
    && unzip cli.zip \
    && rm cli.zip \
    && mv cmdline-tools latest \
    && mkdir -p cmdline-tools \
    && mv latest cmdline-tools/

# Add SDK tools to PATH
ENV PATH="$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools"

# Accept licenses + install SDK packages
RUN yes | sdkmanager --licenses

RUN sdkmanager --update

RUN sdkmanager \
    "platform-tools" \
    "platforms;android-${ANDROID_VERSION}" \
    "build-tools;${ANDROID_BUILD_TOOLS_VERSION}" \
    "ndk;${NDK_VERSION}"

# Basic Linux tools for CI
RUN apt update && apt install -y \
    build-essential \
    file \
    git \
    python3 \
    python3-pip \
    zip \
    unzip

# OPTIONAL: AWS CLI (kept but safer version)
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "/tmp/awscliv2.zip" \
    && unzip /tmp/awscliv2.zip -d /tmp \
    && /tmp/aws/install \
    && rm -rf /tmp/aws /tmp/awscliv2.zip

# Cleanup
RUN apt clean && rm -rf /var/lib/apt/lists/*
