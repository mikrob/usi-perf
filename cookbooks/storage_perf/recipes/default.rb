package 'squid'
package 'elinks'

service "squid3" do
  supports :restart => true
  action auto_compute_action
end

template '/etc/squid3/squid.conf' do
  source "squid.conf.erb"
  owner 'root'
  group 'root'
  mode '0644'
  notifies :restart, resources(:service => "squid3")
end
