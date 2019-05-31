using ExamInfoB.Domain.Entities;
using ExamInfoB.Presentation.Models;
using ExamInfoB.Service.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ExamInfoB.Presentation.Controllers
{
    public class OffreController : Controller
    {
        IExamService Service;
        public OffreController()
        {
            Service = new ExamenService();
        }
        // GET: Offre
        public ActionResult Index()
        {
            List<OffreVM> Offres = new List<OffreVM>();
            foreach (Offre o in Service.ListOffres()) {
                Offres.Add(new OffreVM()
                {
                    IdOffre = o.IdOffre,
                    DatePublication = o.DatePublication,
                    Description = o.Description,
                    TitreOffre = o.TitreOffre,
                    TypeContrat = o.TypeContrat,
                    EntrepriseKey = o.EntrepriseKey,
                    NomEntreprise = o.Entreprise.NomEntreprise,
                    ville = o.Entreprise.Adresse.Ville
                   

                });

            }
            return View(Offres);
        }

        // GET: Offre
        [HttpPost]
        public ActionResult Index(string searchString)
        {
            List<OffreVM> Offres = new List<OffreVM>();

            foreach (Offre o in Service.ListOffres())
            {
                if (o.Entreprise.Adresse.Ville.ToLower().Contains(searchString.ToLower())) {

                    Offres.Add(new OffreVM()
                    {
                        IdOffre = o.IdOffre,
                        DatePublication = o.DatePublication,
                        Description = o.Description,
                        TitreOffre = o.TitreOffre,
                        TypeContrat = o.TypeContrat,
                        EntrepriseKey = o.EntrepriseKey,
                        NomEntreprise = o.Entreprise.NomEntreprise,
                        ville = o.Entreprise.Adresse.Ville


                    });
                }
                

            }
            return View(Offres);
        }
        // GET: Offre/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Offre/Create
        public ActionResult Create()
        {
            var Entreprises = Service.ListEntreprise();
            ViewBag.MyEntreprises = new SelectList(Entreprises, "IdEntreprise", "NomEntreprise");
            return View();
        }

        // POST: Offre/Create
        [HttpPost]
        public ActionResult Create(OffreVM OffreVM)
        {
            try
            {
                // TODO: Add insert logic here
                Offre Offre = new Offre() {
                    TitreOffre = OffreVM.TitreOffre,
                    Description = OffreVM.Description,
                    DatePublication = OffreVM.DatePublication,
                    TypeContrat = OffreVM.TypeContrat,
                    EntrepriseKey = OffreVM.EntrepriseKey
                };
                Service.AddOffre(Offre);

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Offre/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Offre/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add update logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Offre/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Offre/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
