#!/bin/bash
echo 'Creating test database..'
createdb zombie-test
echo 'Building tests..'
gradle test $1
echo 'Deleting test database..'
dropdb zombie-test