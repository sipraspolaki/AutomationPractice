# Use an official Maven image with Java 17 (Eclipse Temurin) as the build environment.
# This image includes Maven CLI so we can run `mvn test` inside the container.
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Install Google Chrome (required for Selenium UI tests).
# We add Google's signing key, add the Chrome repo, then install the stable package.
# `apt-get update` is run before and we clean up apt lists to keep the image small.
RUN apt-get update && \
    apt-get install -y wget gnupg2 && \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Copy the entire project into the container
COPY . .

# Create a non-root user to run tests (best practice for containers).
# Note: ensure the group/user names are consistent and spelled correctly.
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Change ownership of the working directory to the new user
RUN chown -R appuser:appuser /app

# Switch to non-root user for running tests
USER appuser

# Default command: run Maven tests. This will execute TestNG tests defined in the project.
CMD ["mvn", "test"]