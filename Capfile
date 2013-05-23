require 'rubygems'
require 'yaml'
require 'json'
require 'tempfile'
require 'capistrano_colors'
require 'deep_merge'

set :http_proxy, 'http://192.168.56.6:3128'

Dir["lib/*.rb"].each do |file|
  load file
end

set :git_repos_manager_class, 'SimpleGitReposManager'

set :git_repos, [
  {
    :url => "http://github.com/octo-technology/master-chef.git",
    :ref => "986385e218e5819cce41f5c1a8ac300b2cd74d5d"
  },
  {
    :url => "http://github.com/mikrob/usi-perf.git",
    :local_path => ".",
  }
]

require 'master-cap/topology-directory.rb'
