warp_install node.tomcat.user do
  rbenv true
end

file "#{get_home node.tomcat.user}/.gitconfig" do
  mode '0644'
  owner node.tomcat.user
  content <<-EOF
  [user]
  name = Jenkins
  email = jenkins@localhost.com
  EOF
end

template "#{node.maven.home}/apache-maven-#{node.maven.version}/conf/settings.xml" do
    owner node.tomcat.user
    group node.tomcat.user
    source "maven_settings.xml.erb"
end


["config.xml"].each do |x|
  template "#{node.jenkins.home}/#{x}" do
    owner node.tomcat.user
    group node.tomcat.user
    source "#{x}.erb"
    notifies :restart, resources(:service => "jenkins")
  end
end

directory "#{node.jenkins.home}/jobs" do
  owner node.tomcat.user
end


["base_build", "gatling_build"].each do |x|

  directory "#{node.jenkins.home}/jobs/#{x}" do
    owner node.tomcat.user
  end

  template "#{node.jenkins.home}/jobs/#{x}/config.xml" do
    owner node.tomcat.user
    source "jobs/#{x}.xml.erb"
    notifies :restart, resources(:service => "jenkins")
  end

end

ssh_key_private node.tomcat.user

file "#{node.jenkins.home}/ssh_agent.sh" do
  mode '0755'
  owner node.tomcat.user
  content <<-EOF
#!/bin/sh -e

ssh-agent /bin/sh -c "ssh-add && $*"
EOF
end