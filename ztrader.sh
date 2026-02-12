#!/bin/bash

# 设置工作目录为当前脚本所在目录
cd "$(dirname "$0")"

# 定义输出目录和主类
LIB_DIR="lib"
MAIN_CLASS="org.zenoda.ztrader.ZtraderServer"

# 查找output目录下所有的jar文件
JAR_FILES=$(find $LIB_DIR -name "*.jar" | paste -sd ":")

# 检查是否找到了jar文件
if [ -z "$JAR_FILES" ]; then
  echo "没有在$LIB_DIR目录中找到任何jar文件。"
  exit 1
fi

# 使用java命令运行应用
java -cp "$JAR_FILES" $MAIN_CLASS