#!/bin/bash

# 获取脚本所在的绝对路径
SCRIPT_DIR=$(dirname "$(readlink -f "$0")")

# 定义常量
CLIENT_DIR="$SCRIPT_DIR/ztrader-client"
SERVER_DIR="$SCRIPT_DIR/ztrader-server"
OUTPUT_DIR="$SCRIPT_DIR/output"

# 从 package.json 中获取前端版本号
get_frontend_version() {
  cd "$CLIENT_DIR" || exit
  FRONTEND_VERSION=$(grep '"version":' package.json | grep -oP '(?<="version": ")[^"]*')
  cd ..
}

# 从 pom.xml 中获取后端版本号
get_backend_version() {
  cd "$SERVER_DIR" || exit
  BACKEND_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
  cd ..
}

# 清理 output 目录
clean_output_directory() {
  rm -rf "$OUTPUT_DIR" && mkdir -p "$OUTPUT_DIR"
}

# 构建前端
build_frontend() {
  (cd "$CLIENT_DIR" && npm install && npm run build)
}

# 打包前端
package_frontend() {
  (cd "$CLIENT_DIR/dist" && jar cf "$OUTPUT_DIR/ztrader-client-$FRONTEND_VERSION.jar" *)
}

# 构建后端
build_backend() {
  (cd "$SERVER_DIR" && mvn clean package -DskipTests && mvn dependency:copy-dependencies -DoutputDirectory="$OUTPUT_DIR")
}

# 移动后端构建结果
move_backend_jar() {
  find "$SERVER_DIR/target" -name "*.jar" -exec mv {} "$OUTPUT_DIR/ztrader-server-$BACKEND_VERSION.jar" \;
}

# 主流程
get_frontend_version
get_backend_version
clean_output_directory
build_frontend
package_frontend
build_backend
move_backend_jar
