using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace ExamInfoB.Presentation.Models
{
    public class OffreVM
    {
        [DataType(DataType.Date)]
        public DateTime DatePublication { get; set; }
        public string Description { get; set; }
        public int IdOffre { get; set; }
        public string TitreOffre { get; set; }
        public string TypeContrat { get; set; }
        [Display(Name = "Entreprise")]
        public int? EntrepriseKey { get; set; }
        public string ville { get; set; }
        public string NomEntreprise { get; set; }

    }
}