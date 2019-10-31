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
    }
}
