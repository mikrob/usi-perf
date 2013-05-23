require 'rubygems'
require 'yaml'
require 'json'
require 'tempfile'
require 'capistrano_colors'
require 'deep_merge'

Dir["lib/*.rb"].each do |file|
  load file
end

set :git_repos_manager_class, 'SimpleGitReposManager'

set :git_repos, [
  {
    :url => "http://github.com/octo-technology/master-chef.git",
    :ref => "6adcef029586c5ff688ffbe9d5dd7471d27d5b03"
  },
  {
    :url => "http://github.com/mikrob/usi-perf.git",
    :local_path => ".",
  }
]

require 'master-cap/topology-directory.rb'
