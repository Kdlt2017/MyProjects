using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamInfoB.Domain.Entities
{
    public enum Secteur {
           Informatique, Energie, Immobilier, Finance, Autres
    }
    public class Entreprise
    {
        public int ChiffreAffaire { get; set; }
        public Adresse Adresse { get; set; }
        [DataType(DataType.Date)]
        public DateTime DateFondation { get; set; }
        [DataType(DataType.MultilineText)]
        public string Description { get; set; }
        [Range(0,int.MaxValue)]
        public int Effectif { get; set; }
        public int IdEntreprise { get; set; }
        public string NomEntreprise { get; set; }
        public Secteur Secteur { get; set; }

        //Navigation Properties
        public virtual ICollection<Offre> Offres { get; set; }


    }
}
