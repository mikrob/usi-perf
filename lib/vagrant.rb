namespace :vagrant do
  task :create_vm do
    env = check_only_one_env
    TOPOLOGY[env.to_s][:topology].each do |node|
      hostname = TOPOLOGY[env.to_s][:topology][node[0]][:hostname]
      private_ip = TOPOLOGY[env.to_s][:topology][node[0]][:private_ip]
      memory = TOPOLOGY[env.to_s][:topology][node[0]][:memory]
      vagrant_file_content = "
        # -*- mode: ruby -*-
        # vi: set ft=ruby :
        Vagrant::Config.run do |config|
          config.vm.box =\"precise64\"
          config.vm.network :hostonly, \"#{private_ip}\"
          config.vm.host_name = \"#{hostname}\"
          config.vm.customize [\"modifyvm\", :id, \"--memory\", #{memory}]
        end
        "
      %x[mkdir -p vms2/#{hostname}]
      File.open("vms2/#{hostname}/Vagrantfile", 'w') { |file| file.write(vagrant_file_content) }
      puts "cd vms2/#{hostname} && vagrant up"
    end
  end
end