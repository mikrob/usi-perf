default[:app_perf][:tomcat] = {
  :name => 'cocktailfactory',
  :env => {
    'JAVA_OPTS' => '-Xmx512m -Xms128m',
  },
  :directory => '/apps/cocktailfactory'
}

default[:app_perf][:cocktailfactory][:path] = "/cocktailfactory"

