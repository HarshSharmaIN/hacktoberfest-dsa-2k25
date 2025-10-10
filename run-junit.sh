#!/usr/bin/env zsh
set -euo pipefail

ROOT_DIR="$(pwd)"
TOOLS_DIR="$ROOT_DIR/tools/junit"
JUNIT_VERSION="1.10.0"
JAR="$TOOLS_DIR/junit-platform-console-standalone-${JUNIT_VERSION}.jar"

if [[ ! -f "$JAR" ]]; then
  echo "JUnit jar not found. Run tools/download-junit.sh to fetch it."
  echo "Running download script now..."
  "$ROOT_DIR/tools/download-junit.sh"
fi

OUTDIR="$ROOT_DIR/out-junit"
rm -rf "$OUTDIR"
mkdir -p "$OUTDIR"

echo "Collecting Java files with packages..."
FILES=()
while IFS= read -r -d '' f; do
  FILES+=("$f")
done < <(find . -name '*.java' -not -path './.vscode/*' -not -path './tools/*' -print0)

TO_COMPILE=()
for f in "${FILES[@]}"; do
  # compile files that declare package (include test files)
  if grep -qE '^\s*package\s+' "$f" 2>/dev/null; then
    TO_COMPILE+=("$f")
  else
    echo "Skipping default-package or script file: $f"
  fi
done

if [[ ${#TO_COMPILE[@]} -eq 0 ]]; then
  echo "No files to compile."
  exit 1
fi

echo "Compiling ${#TO_COMPILE[@]} files with junit on classpath..."
javac -cp "$JAR" -d "$OUTDIR" "${TO_COMPILE[@]}"

echo "Running JUnit ConsoleLauncher..."
java -jar "$JAR" \
  --class-path "$OUTDIR" \
  --scan-class-path

echo "JUnit run finished."
