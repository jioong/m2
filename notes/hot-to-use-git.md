# git 的简单命令  
  
1. 给命令设置别名  
`git config --global alias.别名 原名`  
2. `git push` 免去输入用户名、密码  
	1. 编辑`.git-credential`文件，内容为`https://{username}:{password}@github.com`  
	2. 添加`git config`内容，命令为`git config --global credential.helper store`  
	3. `.gitconfig`文件中会多出一项，`[credential] helper = store`  
`https`方式每次都要求输入密码，按如下设置可以减少这个困扰：  
	* `git config --global credential.helper cache` 设置记住密码，默认为15分钟  
	* `git config --global credential.helper 'cache --timeout = 3600'` 自己设置时间  
	* `git config --global credential.helper store` 长期存储密码