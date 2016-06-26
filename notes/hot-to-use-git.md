# git 的简单命令  
  
1. 给命令设置别名  
`git config --global alias.别名 原名`  
2. `git push` 免去输入用户名、密码  
	1. 编辑`.git-credential`文件，内容为`https://{username}:{password}@github.com`  
	2. 添加`git config`内容，命令为`git config --global credential.helper store`  
	3. `.gitconfig`文件中会多出一项，`[credential] helper = store`