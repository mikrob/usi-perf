# directory "#{node.jenkins.home}/jobs/#{env}_#{x}" do
#   owner node.tomcat.user
# end

# template "#{node.jenkins.home}/jobs/#{env}_#{x}/config.xml" do
#   owner node.tomcat.user
#   source "jenkins/jobs/#{x}.xml"
#   variables config.merge(:target_env => env)
#   notifies :run, "execute[reload jenkins config]"
# end

file "#{get_home node.tomcat.user}/.gitconfig" do
  mode '0644'
  owner node.tomcat.user
  content <<-EOF
[user]
  name = Jenkins
  email = jenkins@localhost.com
EOF
end


["config.xml"].each do |x|
  template "#{node.jenkins.home}/#{x}" do
    owner node.tomcat.user
    group node.tomcat.user
    source "#{x}.erb"
    notifies :restart, resources(:service => "jenkins")
  end
end