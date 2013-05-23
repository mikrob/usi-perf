include_recipe "warp"
include_recipe "nodejs"

base_user node.deploy_user

warp_install node.deploy_user do
  nvm true
end

nodejs_app "proxy" do
  user node.deploy_user
  directory node.offline_proxy.app_directory
  script "proxy.js"
  opts node.pic.offline_proxy_opts
end

directory "#{node.offline_proxy.app_directory}/shared/storage" do
  owner node.deploy_user
end