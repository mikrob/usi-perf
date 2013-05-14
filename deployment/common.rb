def env_http_proxy
  result = {}
  if exists? :http_proxy
    result['http_proxy'] = http_proxy
    result['https_proxy'] = http_proxy
  end
  result
end