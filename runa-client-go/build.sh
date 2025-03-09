#!/bin/bash
GOOS=windows GOARCH=amd64 go build -o runa-client-go_win.exe
GOOS=darwin GOARCH=amd64 go build -o runa-client-go_macos
GOOS=linux GOARCH=amd64 go build -o runa-client-go_linux
