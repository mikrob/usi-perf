#!/bin/bash

cat /Users/mikael/developpement/infra/master-chef/runtime/bootstrap.sh | ssh vagrant@app.perf bash
cat /Users/mikael/developpement/infra/master-chef/runtime/bootstrap.sh | ssh vagrant@jenkins.perf bash
#cat /Users/mikael/developpement/infra/master-chef/runtime/bootstrap.sh | ssh vagrant@storage.perf bash

# ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAtfWGk8lSEIbwvXSBvK8osch/eU7Uwdq5GsXOihKM5OsxN0VTrmVYro/eL/2fwdTlbgOxYF8bMlgY8EA/IngzxwmMfUpTk2mZ1p6+ScgQI6EVOM4YVvNEcaI2ZvXhnJxm21XQX1Aeh4EwcXac/e6FyT/f32TG+V5eQdE+VhE7iIVxCF/wCUBPDcRnRsbohNRDcaJ8kIM6+rflwU5XozaAOIow8Y/kDDzKZVEBK9LulY9HskLaiuxxI72XW5DnAzoAcbkmw3df9Ve21qFtXY6+ClKfnc2pL+HnrgjPtWa6P/gghEByXsDVFCARu69g3CYrweCsDrSgWU50EsvthxSdZQ== octo-mro@octo-mro-laptop
