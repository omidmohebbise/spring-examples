#!/bin/bash

# Open a new terminal and run runner-1
osascript <<EOF
tell application "Terminal"
    do script "cd $(pwd) && ./gradlew :bootRun"
end tell
EOF

# Open another new terminal and run runner-2
osascript <<EOF
tell application "Terminal"
    do script "cd $(pwd) && ./gradlew :bootRun"
end tell
EOF