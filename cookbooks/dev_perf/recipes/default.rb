
file "/home/chef/fake.log" do
	content "pipo\nchose\nbidule\nplop\npouet\nmolo\n"
end

logrotate_file "test_logrotate" do
    user "chef"
    copytruncate true
    files ["/home/chef/fake.log"]
end