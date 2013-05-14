define :http_check, {
  :mode => '0755',
  :owner => nil,
  :group => nil,
  :url => nil
} do

  template params[:name] do
    cookbook 'perf_base'
    owner params[:owner] if params[:owner]
    group params[:group] if params[:group]
    mode params[:mode]
    source 'http_check.sh.erb'
    variables :url => params[:url]
  end

end