const Diction = require("../models/sys_dictionaries")

class DictionService {

  async find(parentId, name) {
    let pId = null
    if (name) {
      const resName = await Diction.findOne({
        where: { name: name }
      })
      pId = resName.dataValues.id
      // console.log(pId);
    } else {
      pId = parentId
    }
    const res = await Diction.findAll({
      where: { parentId: pId }
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