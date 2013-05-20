
include_recipe "tomcat"
include_recipe "nginx"

base_user node.deploy_user

capistrano_app node.app_perf.tomcat.directory do
  user node.deploy_user
end

war_file = tomcat_instance "cocktailfactory:tomcat" do
  war_location node.app_perf[:cocktailfactory][:path]
end

tomcat_app_http_port = tomcat_config("cocktailfactory:tomcat")[:connectors][:http][:port]

nginx_add_default_location "cocktailfactory" do
  content <<-EOF

  location / {
    proxy_pass http://tomcat_iam_upstream;
    break;
  }

EOF
  upstream <<-EOF
  upstream tomcat_iam_upstream {
  server 127.0.0.1:#{tomcat_app_http_port} fail_timeout=0;
}
  EOF
end

sudo_sudoers_file "deploy_to_tomcat" do
  content <<-EOF
deploy ALL = (tomcat) NOPASSWD: #{node.app_perf.tomcat.directory}/deploy.sh
EOF
end

template "/apps/cocktailfactory/deploy.sh" do
  owner node.deploy_user
  source "deploy.sh.erb"
  mode '0755'
  variables :war_file => war_file
end

http_check "/apps/cocktailfactory/http_check.sh" do
  owner node.deploy_user
  url "http://localhost:8080/cocktailfactory/version"
end