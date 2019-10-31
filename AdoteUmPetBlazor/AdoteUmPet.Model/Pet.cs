using System;

namespace AdoteUmPet.Model
{
    public class Pet
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public DateTime RegisteredDate { get; set; }
        public int? ShelterId { get; set; }
        public int BreedyId { get; set; }
        public string Description { get; set; }
        public int Age { get; set; }
        public int SpecieId { get; set; }
        public Specie Specie { get; set; }
        public Breedy Breedy { get; set; }
        public Shelter Shelter { get; set; }
    }
}
