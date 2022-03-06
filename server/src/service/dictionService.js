const Diction = require("../models/sys_dictionaries")

class DictionService {

  async find(parentId) {
    const res = await Diction.findAll({
      where: { parentId: parentId }
    })

    return res
  }

  async add(name, parentId) {
    const res = await Diction.create({
      parentId: parentId, name: name
    })

    return res
  }

  async deleteD(id) {
    const res = await Diction.destroy({
      where: { id: id }
    })

    return res
  }
}

module.exports = new DictionService()