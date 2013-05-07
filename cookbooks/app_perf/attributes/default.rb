default[:app_perf][:tomcat] = {
  :name => 'app',
  :env => {
    'JAVA_OPTS' => '-Xmx512m -Xms128m',
  },
  :directory => '/apps/app_webapp'
}