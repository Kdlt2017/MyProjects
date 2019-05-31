using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamInfoB.Domain.Entities
{
    public class Postulant
    {
        [DataType(DataType.Date)]
        public DateTime DateNaissance { get; set; }
        public string Email { get; set; }
        public int IdPostulant { get; set; }
        public string Nom { get; set; }
        public string Prenom { get; set; }

        //Navigation Properties
        public virtual ICollection<Offre> Offres { get; set; }
    }
}
