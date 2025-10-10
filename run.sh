#!/usr/bin/env zsh
set -euo pipefail

# Simple run script for this repository
# - compiles all .java files that do NOT import junit/jupiter
# - outputs classes to ./out
# - runs the given main class (default: tests.TestRunner)

OUTDIR="out"

echo "Cleaning $OUTDIR"
rm -rf "$OUTDIR"
mkdir -p "$OUTDIR"

echo "Collecting Java files..."
# find all .java files, excluding .vscode and out
FILES=()
# Use null-delimited output to handle spaces in filenames
while IFS= read -r -d '' f; do
  FILES+=("$f")
done < <(find . -name '*.java' -not -path './.vscode/*' -not -path './out/*' -print0)

TO_COMPILE=()
for f in "${FILES[@]}"; do
  # Only compile files that declare a package (skip default-package snippets that cause duplicate class names)
  if ! grep -qE '^\s*package\s+' "$f" 2>/dev/null; then
    echo "Skipping file without package: $f"
    continue
  fi

  # Skip junit test files because JUnit jars are not configured in this script
  if grep -qE 'org\\.junit|junit' "$f" 2>/dev/null || grep -qE '@Test|@BeforeEach|@AfterEach|@BeforeAll|@AfterAll' "$f" 2>/dev/null; then
    echo "Skipping junit test file: $f"
    continue
  fi

  TO_COMPILE+=("$f")
done

if [[ ${#TO_COMPILE[@]} -eq 0 ]]; then
  echo "No Java files to compile after filtering junit tests. Exiting."
  exit 1
fi

echo "Compiling ${#TO_COMPILE[@]} files..."
javac -d "$OUTDIR" "${TO_COMPILE[@]}"

# Main class to run (first arg) or default to tests.TestRunner
MAIN_CLASS=${1:-tests.TestRunner}

echo "Running $MAIN_CLASS"
java -cp "$OUTDIR" "$MAIN_CLASS"

echo "Done."
