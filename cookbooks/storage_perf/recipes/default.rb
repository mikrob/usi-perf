base_user "git"

service "ssh" do
  supports :restart => true
  action auto_compute_action
end

template "/etc/ssh/ssh_host_ecdsa_key" do
  source "ssh_host_ecdsa_key.erb"
  owner 'root'
  group 'root'
  mode '0600'
  notifies :restart, "service[ssh]"
end

template "/etc/ssh/ssh_host_ecdsa_key.pub" do
  source "ssh_host_ecdsa_key.pub.erb"
  owner 'root'
  group 'root'
  mode '0644'
  notifies :restart, "service[ssh]"
end