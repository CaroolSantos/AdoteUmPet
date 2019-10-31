using System;
using System.Collections.Generic;
using System.Text;

namespace AdoteUmPet.Model
{
    public class State
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public List<City> Cities { get; set; }
    }
}
