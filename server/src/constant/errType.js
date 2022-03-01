module.exports = {
  userFormateError: {
    code: '101',
    data: {
      message: "用户名或密码为空",
    }
  },
  userAlreadyExited: {
    code: '102',
    data: {
      message: "用户已经存在",
    }
  },
  userRegisterError: {
    code: '103',
    data: {
      message: "登录出错",
    }
  },
  userDoesNotExist: {
    code: '104',
    data: {
      message: "用户不存在",
    }
  },
  userLoginError: {
    code: '105',
    data: {
      message: "用户登录失败",
    }
  },
  invalidPassword: {
    code: '106',
    data: {
      message: "密码不匹配",
    }
  },
  userNotApproved: {
    code: '107',
    data: {
      message: "用户还未通过审核",
    }
  },
  tokenExpiredError: {
    code: '201',
    data: {
      message: "token已过期",
    }
  },
  invalidToken: {
    code: '202',
    data: {
      message: "无效token",
    }
  }

}