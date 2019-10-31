using System;
using System.Collections.Generic;
using System.Text;

namespace AdoteUmPet.Model
{
    public class Specie
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public List<Pet> Pets { get; set; }
    }
}
