#!/usr/bin/env zsh
set -euo pipefail

# Download JUnit Platform Console Standalone jar into tools/junit
JUNIT_DIR="$(dirname "$0")/junit"
mkdir -p "$JUNIT_DIR"

# Use a specific stable version. Update this if you want a newer one.
JUNIT_VERSION="1.10.0"
JAR_NAME="junit-platform-console-standalone-${JUNIT_VERSION}.jar"
URL="https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/${JUNIT_VERSION}/${JAR_NAME}"

if [[ -f "$JUNIT_DIR/$JAR_NAME" ]]; then
  echo "JUnit jar already exists: $JUNIT_DIR/$JAR_NAME"
  exit 0
fi

echo "Downloading JUnit Console Standalone ${JUNIT_VERSION}..."
curl -L -o "$JUNIT_DIR/$JAR_NAME" "$URL"
echo "Downloaded to $JUNIT_DIR/$JAR_NAME"
