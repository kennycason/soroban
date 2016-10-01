#!/bin/sh

PROGRAM_NAME=soroban
BINARY_DIR=/usr/local/bin/
BINARY=$BINARY_DIR$PROGRAM_NAME
APP_DIR=/usr/local/opt/$PROGRAM_NAME/

rm $BINARY
rm -R $APP_DIR
