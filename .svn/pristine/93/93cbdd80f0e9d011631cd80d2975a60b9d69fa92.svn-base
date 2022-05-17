package com.ampamt.moduler.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ampamt.moduler.bean.AmpamtAccountBean;
import com.ampamt.moduler.bean.AmpamtAuditTrailBean;
import com.ampamt.moduler.bean.AmpamtTherapiesBean;
import com.ampamt.moduler.bean.BusinessAccountDetailsBean;
import com.ampamt.moduler.bean.BusinessDocumentsDetailsBean;
import com.ampamt.moduler.bean.DoctorsAccountDetailsBean;
import com.ampamt.moduler.bean.DoctorsDocumentsDetailsBean;
import com.ampamt.moduler.common.CustomizedResultTransformer;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.model.ChangePasswordModel;
import com.ampamt.moduler.model.DoctorAccountStatusModel;
import com.ampamt.moduler.model.DocumentsVerificationModel;
import com.ampamt.moduler.model.SuccessModel;
import com.ampamt.moduler.model.UserSearchModel;
import com.ampamt.moduler.security.AES;
import com.ampamt.moduler.util.DumpBean;

@Repository
public class AccountDaoImpl implements AccountDao {

	private static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	protected Session currentSession() {

		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

		} catch (Exception ex) {
			logger.debug(" ++++ getSession catch ++++ ", ex);
			session = sessionFactory.openSession();
		}
		logger.debug("current session==>" + session);
		logger.debug("current sessionFactory==>" + sessionFactory.hashCode());
		return session;
	}

	@Override
	public SuccessModel createDoctorsAccountDetails(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("***************start createDoctorsAccountDetails******************");
		
		Session session = currentSession();

		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {
			session.getTransaction().begin();
			doctorsAccountDetailsBean.setCreateDate(new Date());
//			doctorsAccountDetailsBean.setIsAdmin(ApplicationConstant.FLAG_N);
			logger.debug("createDoctorsAccountDetails DumpBean="+DumpBean.dumpBean(doctorsAccountDetailsBean));
			status = (String) session.save(doctorsAccountDetailsBean);
			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;
			successModel.setId(doctorsAccountDetailsBean.getId());
		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status createDoctorsAccountDetails save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end createDoctorsAccountDetails***************************");
		return successModel;
	}

	@Override
	public SuccessModel createAccountId(AmpamtAccountBean ampamtAccount) {
		logger.debug("***************start createAccountId******************");

		String accountNo = new SimpleDateFormat("HHmmssyy").format(Calendar.getInstance().getTime());
		accountNo = "AMPAMT" + accountNo;
		logger.debug("accountNo=" + accountNo);
		Session session = currentSession();
		
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

			session.getTransaction().begin();
			ampamtAccount.setId(accountNo);
			ampamtAccount.setUsername(ampamtAccount.getEmailId());
			status = (String) session.save(ampamtAccount);
			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;

			successModel.setId(accountNo);
		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status createAccountId save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end createAccountId***************************");
		return successModel;

	}

	@Override
	public SuccessModel updateDoctorAccountDetails(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("***************start updateDoctorAccountDetails******************");
		DoctorsAccountDetailsBean doctorsAccountDetailsBeanOld = new DoctorsAccountDetailsBean();
		List<DoctorsAccountDetailsBean> doctorsAccountDetailsBeanList = getDoctordetail(doctorsAccountDetailsBean);

		Session session = currentSession();
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

			if (doctorsAccountDetailsBeanList.size() > 0) {
				doctorsAccountDetailsBeanOld = doctorsAccountDetailsBeanList.get(0);
				session.getTransaction().begin();

				if (doctorsAccountDetailsBean.getAadharNo() != null) {
					doctorsAccountDetailsBeanOld.setAadharNo(doctorsAccountDetailsBean.getAadharNo());
				}

				if (doctorsAccountDetailsBean.getAccountType() != null) {
					doctorsAccountDetailsBeanOld.setAccountType(doctorsAccountDetailsBean.getAccountType());
				}

				if (doctorsAccountDetailsBean.getActiveFlag() != null) {
					doctorsAccountDetailsBeanOld.setActiveFlag(doctorsAccountDetailsBean.getActiveFlag());
				}

				if (doctorsAccountDetailsBean.getBloodGroup() != null) {
					doctorsAccountDetailsBeanOld.setBloodGroup(doctorsAccountDetailsBean.getBloodGroup());
				}

				if (doctorsAccountDetailsBean.getCompanyName() != null) {
					doctorsAccountDetailsBeanOld.setCompanyName(doctorsAccountDetailsBean.getCompanyName());
				}

				if (doctorsAccountDetailsBean.getCompanyTitle() != null) {
					doctorsAccountDetailsBeanOld.setCompanyTitle(doctorsAccountDetailsBean.getCompanyTitle());
				}

				if (doctorsAccountDetailsBean.getContactNo() != null) {
					doctorsAccountDetailsBeanOld.setContactNo(doctorsAccountDetailsBean.getContactNo());
				}

				if (doctorsAccountDetailsBean.getDob() != null) {
					doctorsAccountDetailsBeanOld.setDob(doctorsAccountDetailsBean.getDob());
				}

				if (doctorsAccountDetailsBean.getEmailId() != null) {
					doctorsAccountDetailsBeanOld.setEmailId(doctorsAccountDetailsBean.getEmailId());
				}
				if (doctorsAccountDetailsBean.getWifeHusbandName() != null) {
					doctorsAccountDetailsBeanOld.setWifeHusbandName(doctorsAccountDetailsBean.getWifeHusbandName());
				}
				if (doctorsAccountDetailsBean.getEmergencyNo() != null) {
					doctorsAccountDetailsBeanOld.setEmergencyNo(doctorsAccountDetailsBean.getEmergencyNo());
				}
				if (doctorsAccountDetailsBean.getExperiencEstablishment() != null) {
					doctorsAccountDetailsBeanOld
							.setExperiencEstablishment(doctorsAccountDetailsBean.getExperiencEstablishment());
				}
				if (doctorsAccountDetailsBean.getFatherName() != null) {
					doctorsAccountDetailsBeanOld.setFatherName(doctorsAccountDetailsBean.getFatherName());
				}
				if (doctorsAccountDetailsBean.getGender() != null) {
					doctorsAccountDetailsBeanOld.setGender(doctorsAccountDetailsBean.getGender());
				}
//				if (doctorsAccountDetailsBean.getIsAdmin() != null) {
//					doctorsAccountDetailsBeanOld.setIsAdmin(doctorsAccountDetailsBean.getIsAdmin());
//				}
				if (doctorsAccountDetailsBean.getMaritalStatus() != null) {
					doctorsAccountDetailsBeanOld.setMaritalStatus(doctorsAccountDetailsBean.getMaritalStatus());
				}
				if (doctorsAccountDetailsBean.getMotherName() != null) {
					doctorsAccountDetailsBeanOld.setMotherName(doctorsAccountDetailsBean.getMotherName());
				}
				if (doctorsAccountDetailsBean.getFirstName() != null) {
					doctorsAccountDetailsBeanOld.setFirstName(doctorsAccountDetailsBean.getFirstName());
				}
				if (doctorsAccountDetailsBean.getMiddleName() != null) {
					doctorsAccountDetailsBeanOld.setMiddleName(doctorsAccountDetailsBean.getMiddleName());
				}
				if (doctorsAccountDetailsBean.getLastName() != null) {
					doctorsAccountDetailsBeanOld.setLastName(doctorsAccountDetailsBean.getLastName());
				}
				if (doctorsAccountDetailsBean.getNameTitle() != null) {
					doctorsAccountDetailsBeanOld.setNameTitle(doctorsAccountDetailsBean.getNameTitle());
				}
				if (doctorsAccountDetailsBean.getNationality() != null) {
					doctorsAccountDetailsBeanOld.setNationality(doctorsAccountDetailsBean.getNationality());
				}
				if (doctorsAccountDetailsBean.getOtherContactNo() != null) {
					doctorsAccountDetailsBeanOld.setOtherContactNo(doctorsAccountDetailsBean.getOtherContactNo());
				}
				if (doctorsAccountDetailsBean.getPanNo() != null) {
					doctorsAccountDetailsBeanOld.setPanNo(doctorsAccountDetailsBean.getPanNo());
				}
				if (doctorsAccountDetailsBean.getPassportNo() != null) {
					doctorsAccountDetailsBeanOld.setPassportNo(doctorsAccountDetailsBean.getPassportNo());
				}
				if (doctorsAccountDetailsBean.getPracticeCity() != null) {
					doctorsAccountDetailsBeanOld.setPracticeCity(doctorsAccountDetailsBean.getPracticeCity());
				}
				if (doctorsAccountDetailsBean.getPracticing() != null) {
					doctorsAccountDetailsBeanOld.setPracticing(doctorsAccountDetailsBean.getPracticing());
				}
				if (doctorsAccountDetailsBean.getPracticingAs() != null) {
					doctorsAccountDetailsBeanOld.setPracticingAs(doctorsAccountDetailsBean.getPracticingAs());
				}
				if (doctorsAccountDetailsBean.getProfilePic() != null) {
					doctorsAccountDetailsBeanOld.setProfilePic(doctorsAccountDetailsBean.getProfilePic());
				}
				if (doctorsAccountDetailsBean.getRegistOfficeAdd() != null) {
					doctorsAccountDetailsBeanOld.setRegistOfficeAdd(doctorsAccountDetailsBean.getRegistOfficeAdd());
				}
				if (doctorsAccountDetailsBean.getReligion() != null) {
					doctorsAccountDetailsBeanOld.setReligion(doctorsAccountDetailsBean.getReligion());
				}
				if (doctorsAccountDetailsBean.getStatus() != null) {
					doctorsAccountDetailsBeanOld.setStatus(doctorsAccountDetailsBean.getStatus());
				}
				if (doctorsAccountDetailsBean.getTncAccepted() != null) {
					doctorsAccountDetailsBeanOld.setTncAccepted(doctorsAccountDetailsBean.getTncAccepted());
				}
				if (doctorsAccountDetailsBean.getWhatsappNo() != null) {
					doctorsAccountDetailsBeanOld.setWhatsappNo(doctorsAccountDetailsBean.getWhatsappNo());
				}

				if (doctorsAccountDetailsBean.getPermanentAddress() != null) {
					doctorsAccountDetailsBeanOld.setPermanentAddress(doctorsAccountDetailsBean.getPermanentAddress());
				}
				
				if (doctorsAccountDetailsBean.getCity() != null) {
					doctorsAccountDetailsBeanOld.setCity(doctorsAccountDetailsBean.getCity());
				}
				if (doctorsAccountDetailsBean.getZipCode() != null) {
					doctorsAccountDetailsBeanOld.setZipCode(doctorsAccountDetailsBean.getZipCode());
				}
				
				if (doctorsAccountDetailsBean.getState() != null) {
					doctorsAccountDetailsBeanOld.setState(doctorsAccountDetailsBean.getState());
				}
				
				if (doctorsAccountDetailsBean.getCountry() != null) {
					doctorsAccountDetailsBeanOld.setCountry(doctorsAccountDetailsBean.getCountry());
				}
				
				if (doctorsAccountDetailsBean.getReference() != null) {
					doctorsAccountDetailsBeanOld.setReference(doctorsAccountDetailsBean.getReference());
				}

				session.update(doctorsAccountDetailsBeanOld);
				session.getTransaction().commit();
				status = ApplicationConstant.STATUS_SUCCESS;
				successModel.setId(doctorsAccountDetailsBean.getId());
			} else {
				status = ApplicationConstant.STATUS_FAILED;
			}

		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status updateDoctorAccountDetails save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end updateDoctorAccountDetails***************************");
		return successModel;

	}

	@Override
	public SuccessModel deleteDoctorAccountDetails(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("************************start deleteDoctorAccountDetails***************************");

		SuccessModel successModel = new SuccessModel();
		String status = null;
		Session session = currentSession();
		try {

			session.getTransaction().begin();
			session.remove(doctorsAccountDetailsBean);
			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;
		} catch (Exception e) {
			logger.debug("exception during delete>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("deleteDoctorAccountDetails delete=" + status);
		successModel.setStatus(status);
		logger.debug("************************end deleteDoctorAccountDetails***************************");
		return successModel;
	}

	@Override
	public SuccessModel createDoctorsDocumentsDetails(DoctorsDocumentsDetailsBean doctorsDocumentsDetailsBean) {
		logger.debug("***************start createDoctorsDocumentsDetails******************");

		String documentId = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		Session session = currentSession();
		documentId = "DOCM" + documentId;
		logger.debug("documentId=" + documentId);
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

			session.getTransaction().begin();
			doctorsDocumentsDetailsBean.setDocumentId(documentId);
			status = (String) session.save(doctorsDocumentsDetailsBean);
			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;
			successModel.setId(documentId);
		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status createDoctorsDocumentsDetails save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end createDoctorsDocumentsDetails***************************");
		return successModel;

	}

	@Override
	public List<DoctorsAccountDetailsBean> getDoctordetail(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("***************start getDoctordetail******************");
		Session session = currentSession();
		List<DoctorsAccountDetailsBean> doctorsAccountDetailsList = new ArrayList<DoctorsAccountDetailsBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(DoctorsAccountDetailsBean.class);
			if (doctorsAccountDetailsBean.getId() != null) {
//				AES.setKey("");
//				AES.decrypt(doctorsAccountDetailsBean.getId());
				cr.add(Restrictions.eq("id", doctorsAccountDetailsBean.getId()));
				doctorsAccountDetailsList = cr.list();
			}
			logger.debug("doctorsAccountDetailsList size=" + doctorsAccountDetailsList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getDoctordetail******************");

		return doctorsAccountDetailsList;
	}

	@Override
	public List<DoctorsAccountDetailsBean> getDoctorsLists(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("***************start getDoctorsLists******************");
		Session session = currentSession();

		List<DoctorsAccountDetailsBean> doctorsAccountDetailsList = new ArrayList<DoctorsAccountDetailsBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(DoctorsAccountDetailsBean.class);
			if (doctorsAccountDetailsBean.getId() != null) {
				cr.add(Restrictions.eq("id", doctorsAccountDetailsBean.getId()));

			}

			if (doctorsAccountDetailsBean.getEmailId() != null) {
				cr.add(Restrictions.eq("emailId", doctorsAccountDetailsBean.getEmailId()));

			}

			if (doctorsAccountDetailsBean.getCompanyName() != null) {
				cr.add(Restrictions.eq("companyName", doctorsAccountDetailsBean.getCompanyName()));

			}

			if (doctorsAccountDetailsBean.getContactNo() != null) {
				cr.add(Restrictions.eq("contactNo", doctorsAccountDetailsBean.getContactNo()));

			}
			cr.addOrder(Order.desc("createDate"));
			doctorsAccountDetailsList = cr.list();

			logger.debug("doctorsAccountDetailsList size=" + doctorsAccountDetailsList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getDoctorsLists******************");

		return doctorsAccountDetailsList;
	}

	@Override
	public List<DoctorsDocumentsDetailsBean> getDocumentdetail(
			DoctorsDocumentsDetailsBean doctorsDocumentsDetailsBean) {
		logger.debug("***************start getDocumentdetail******************");
		Session session = currentSession();

		List<DoctorsDocumentsDetailsBean> doctorsDocumentsDetailsList = new ArrayList<DoctorsDocumentsDetailsBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(DoctorsDocumentsDetailsBean.class);

			if (doctorsDocumentsDetailsBean.getId() != null || doctorsDocumentsDetailsBean.getDocumentId() != null) {

				if (doctorsDocumentsDetailsBean.getId() != null) {
//					AES.setKey("");
//					AES.decrypt(doctorsDocumentsDetailsBean.getId());
					cr.add(Restrictions.eq("id", doctorsDocumentsDetailsBean.getId()));
				}

				if (doctorsDocumentsDetailsBean.getDocumentId() != null) {
					cr.add(Restrictions.eq("documentId", doctorsDocumentsDetailsBean.getDocumentId()));
				}

				doctorsDocumentsDetailsList = cr.list();
			}

			logger.debug("doctorsDocumentsDetailsList size=" + doctorsDocumentsDetailsList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getDocumentdetail******************");

		return doctorsDocumentsDetailsList;
	}

	@Override
	public SuccessModel updateDoctorDocuments(DoctorsDocumentsDetailsBean doctorsDocumentsDetailsBean) {
		logger.debug("***************start updateDoctorDocuments******************");
		DoctorsDocumentsDetailsBean doctorsDocumentsDetailsOld = new DoctorsDocumentsDetailsBean();
		List<DoctorsDocumentsDetailsBean> doctorsDocumentsDetailsList = getDocumentdetail(doctorsDocumentsDetailsBean);
		if (doctorsDocumentsDetailsList.size() == 0) {
			createDoctorsDocumentsDetails(doctorsDocumentsDetailsBean);
			doctorsDocumentsDetailsList = getDocumentdetail(doctorsDocumentsDetailsBean);
		}
		Session session = currentSession();
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

			if (doctorsDocumentsDetailsList.size() > 0) {
				doctorsDocumentsDetailsOld = doctorsDocumentsDetailsList.get(0);
				session.getTransaction().begin();
				if (doctorsDocumentsDetailsBean.getProfilePic() != null) {
					doctorsDocumentsDetailsOld.setProfilePic(doctorsDocumentsDetailsBean.getProfilePic());
				}
				
				if (doctorsDocumentsDetailsBean.getAadhar() != null) {
					doctorsDocumentsDetailsOld.setAadhar(doctorsDocumentsDetailsBean.getAadhar());
				}
				
				if (doctorsDocumentsDetailsBean.getAadharBack() != null) {
					doctorsDocumentsDetailsOld.setAadharBack(doctorsDocumentsDetailsBean.getAadharBack());
				}
				
				if (doctorsDocumentsDetailsBean.getPanFront() != null) {
					doctorsDocumentsDetailsOld.setPanFront(doctorsDocumentsDetailsBean.getPanFront());
				}
				
				if (doctorsDocumentsDetailsBean.getPanBack() != null) {
					doctorsDocumentsDetailsOld.setPanBack(doctorsDocumentsDetailsBean.getPanBack());
				}

				if (doctorsDocumentsDetailsBean.getQualiCertificate1() != null) {
					doctorsDocumentsDetailsOld.setQualiCertificate1(doctorsDocumentsDetailsBean.getQualiCertificate1());
				}
				if (doctorsDocumentsDetailsBean.getQualiCertificate1() != null) {
					doctorsDocumentsDetailsOld.setQualiCertificate1(doctorsDocumentsDetailsBean.getQualiCertificate1());
				}
				if (doctorsDocumentsDetailsBean.getQualiCertificate2() != null) {
					doctorsDocumentsDetailsOld.setQualiCertificate2(doctorsDocumentsDetailsBean.getQualiCertificate2());
				}
				if (doctorsDocumentsDetailsBean.getQualiCertificate3() != null) {
					doctorsDocumentsDetailsOld.setQualiCertificate3(doctorsDocumentsDetailsBean.getQualiCertificate3());
				}
				if (doctorsDocumentsDetailsBean.getQualiCertificate4() != null) {
					doctorsDocumentsDetailsOld.setQualiCertificate4(doctorsDocumentsDetailsBean.getQualiCertificate4());
				}

				if (doctorsDocumentsDetailsBean.getQualiCertificate5() != null) {
					doctorsDocumentsDetailsOld.setQualiCertificate5(doctorsDocumentsDetailsBean.getQualiCertificate5());
				}
				if (doctorsDocumentsDetailsBean.getQualiCertificate6() != null) {
					doctorsDocumentsDetailsOld.setQualiCertificate6(doctorsDocumentsDetailsBean.getQualiCertificate6());
				}
				if (doctorsDocumentsDetailsBean.getRampCertificate() != null) {
					doctorsDocumentsDetailsOld.setRampCertificate(doctorsDocumentsDetailsBean.getRampCertificate());
				}
				if (doctorsDocumentsDetailsBean.getProfCertificate() != null) {
					doctorsDocumentsDetailsOld.setProfCertificate(doctorsDocumentsDetailsBean.getProfCertificate());
				}
				if (doctorsDocumentsDetailsBean.getHealthDepCertificate() != null) {
					doctorsDocumentsDetailsOld
							.setHealthDepCertificate(doctorsDocumentsDetailsBean.getHealthDepCertificate());
				}
				if (doctorsDocumentsDetailsBean.getMediCouncilRegistration() != null) {
					doctorsDocumentsDetailsOld
							.setMediCouncilRegistration(doctorsDocumentsDetailsBean.getMediCouncilRegistration());
				}
				if (doctorsDocumentsDetailsBean.getAyushDepRegistration() != null) {
					doctorsDocumentsDetailsOld
							.setAyushDepRegistration(doctorsDocumentsDetailsBean.getAyushDepRegistration());
				}
				if (doctorsDocumentsDetailsBean.getOtherLicCertificate1() != null) {
					doctorsDocumentsDetailsOld
							.setOtherLicCertificate1(doctorsDocumentsDetailsBean.getOtherLicCertificate1());
				}
				if (doctorsDocumentsDetailsBean.getOtherLicCertificate2() != null) {
					doctorsDocumentsDetailsOld
							.setOtherLicCertificate2(doctorsDocumentsDetailsBean.getOtherLicCertificate2());
				}
				if (doctorsDocumentsDetailsBean.getOtherLicCertificate3() != null) {
					doctorsDocumentsDetailsOld
							.setOtherLicCertificate3(doctorsDocumentsDetailsBean.getOtherLicCertificate3());
				}
				if (doctorsDocumentsDetailsBean.getProfessionalWorkimg1() != null) {
					doctorsDocumentsDetailsOld
							.setProfessionalWorkimg1(doctorsDocumentsDetailsBean.getProfessionalWorkimg1());
				}

				if (doctorsDocumentsDetailsBean.getProfessionalWorkimg2() != null) {
					doctorsDocumentsDetailsOld
							.setProfessionalWorkimg2(doctorsDocumentsDetailsBean.getProfessionalWorkimg2());
				}
				if (doctorsDocumentsDetailsBean.getProfessionalWorkimg3() != null) {
					doctorsDocumentsDetailsOld
							.setProfessionalWorkimg3(doctorsDocumentsDetailsBean.getProfessionalWorkimg3());
				}

				if (doctorsDocumentsDetailsBean.getExperienceComments() != null) {
					doctorsDocumentsDetailsOld
							.setExperienceComments(doctorsDocumentsDetailsBean.getExperienceComments());
				}
				if (doctorsDocumentsDetailsBean.getAchievementsComments() != null) {
					doctorsDocumentsDetailsOld
							.setAchievementsComments(doctorsDocumentsDetailsBean.getAchievementsComments());
				}

				if (doctorsDocumentsDetailsBean.getWebsiteLink() != null) {
					doctorsDocumentsDetailsOld.setWebsiteLink(doctorsDocumentsDetailsBean.getWebsiteLink());
				}

				if (doctorsDocumentsDetailsBean.getInstagramLink() != null) {
					doctorsDocumentsDetailsOld.setInstagramLink(doctorsDocumentsDetailsBean.getInstagramLink());
				}

				if (doctorsDocumentsDetailsBean.getYoutubeLink() != null) {
					doctorsDocumentsDetailsOld.setYoutubeLink(doctorsDocumentsDetailsBean.getYoutubeLink());
				}

				if (doctorsDocumentsDetailsBean.getFacebookLink() != null) {
					doctorsDocumentsDetailsOld.setFacebookLink(doctorsDocumentsDetailsBean.getFacebookLink());
				}

				if (doctorsDocumentsDetailsBean.getTwitterLink() != null) {
					doctorsDocumentsDetailsOld.setTwitterLink(doctorsDocumentsDetailsBean.getTwitterLink());
				}

				if (doctorsDocumentsDetailsBean.getSignatureImg() != null) {
					doctorsDocumentsDetailsOld.setSignatureImg(doctorsDocumentsDetailsBean.getSignatureImg());
				}

				session.update(doctorsDocumentsDetailsOld);
				session.getTransaction().commit();
				status = ApplicationConstant.STATUS_SUCCESS;
				successModel.setId(doctorsDocumentsDetailsOld.getDocumentId());
			} else {
				status = ApplicationConstant.STATUS_FAILED;
			}

		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status updateDoctorDocuments save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end updateDoctorDocuments***************************");
		return successModel;

	}

	@Override
	public SuccessModel doctorLogin(AmpamtAccountBean ampamtAccountBean) {

		logger.debug("***************start doctorLogin******************");

		logger.debug("ampamtAccountBean.getEmailId()="+ampamtAccountBean.getEmailId());
		SuccessModel successModel = new SuccessModel();
		Session session = currentSession();
		List<AmpamtAccountBean> ampamtAccountList = new ArrayList<AmpamtAccountBean>();
		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtAccountBean.class);

			Criterion emailId = Restrictions.eq("emailId", ampamtAccountBean.getEmailId().trim().toLowerCase());
			Criterion contactNo = Restrictions.eq("contactNo", ampamtAccountBean.getEmailId().trim().toLowerCase());
			LogicalExpression orExp = Restrictions.or(emailId, contactNo);
			cr.add(orExp);
//			cr.add(Restrictions.eq("username", ampamtAccount.getUsername()));
			cr.add(Restrictions.eq("accountType", ApplicationConstant.ACCOUNT_DOCTOR));
			AES.setKey("");
			AES.encrypt(ampamtAccountBean.getPassword());
			cr.add(Restrictions.eq("password", AES.getEncryptedString()));

			ampamtAccountList = cr.list();

			logger.debug("ampamtAccountList size=" + ampamtAccountList.size());
			successModel.setAccountType(ApplicationConstant.ACCOUNT_DOCTOR);
			if (!ampamtAccountList.isEmpty() && ampamtAccountList.size() > 0) {
				ampamtAccountBean = (AmpamtAccountBean) ampamtAccountList.get(0);
				if (ampamtAccountBean != null) {
//					AES.encrypt(ampamtAccount.getId());
					successModel.setId(ampamtAccountBean.getId());
					successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
					successModel.setActiveFlag(ampamtAccountBean.getActiveFlag());
					successModel.setEmailId(ampamtAccountBean.getEmailId());
					
				} else {
					successModel.setStatus(ApplicationConstant.STATUS_FAILED);
					logger.debug("invalid password");
				}

			} else {
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
				
				logger.debug("invalid password");
				successModel.setMsg("invalid login");
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		successModel.setEmailId(ampamtAccountBean.getEmailId());
		logger.debug("***************end doctorLogin******************");
		return successModel;

	}

	@Override
	public SuccessModel deleteBusinessAccountDetails(BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("************************start deleteBusinessAccountDetails***************************");

		SuccessModel successModel = new SuccessModel();
		String status = null;
		Session session = currentSession();
		try {

			session.getTransaction().begin();
			session.remove(businessAccountDetailsBean);
			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;
		} catch (Exception e) {
			logger.debug("exception during delete>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("deleteBusinessAccountDetails delete=" + status);
		successModel.setStatus(status);
		logger.debug("************************end deleteBusinessAccountDetails***************************");
		return successModel;
	}

	@Override
	public SuccessModel createBusinessAccountDetails(BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("***************start createBusinessAccountDetails******************");

//		String accountNo=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		Session session = currentSession();
//		accountNo="AC"+accountNo;
//		logger.debug("accountNo="+accountNo);
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {
			session.getTransaction().begin();
			businessAccountDetailsBean.setCreateDate(new Date());
			status = (String) session.save(businessAccountDetailsBean);
			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;
			successModel.setId(businessAccountDetailsBean.getId());
		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status createBusinessAccountDetails save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end createBusinessAccountDetails***************************");
		return successModel;
	}

	@Override
	public SuccessModel createBusinessDocumentsDetails(BusinessDocumentsDetailsBean businessDocumentsDetailsBean) {
		logger.debug("***************start createBusinessDocumentsDetails******************");

		String documentId = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		Session session = currentSession();
		documentId = "DOCM" + documentId;
		logger.debug("documentId=" + documentId);
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

			session.getTransaction().begin();
			businessDocumentsDetailsBean.setDocumentId(documentId);
			status = (String) session.save(businessDocumentsDetailsBean);
			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;
			successModel.setId(documentId);
		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status createBusinessDocumentsDetails save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end createBusinessDocumentsDetails***************************");
		return successModel;

	}

	@Override
	public List<BusinessDocumentsDetailsBean> getBusinessDocumentdetail(
			BusinessDocumentsDetailsBean businessDocumentsDetailsBean) {
		logger.debug("***************start getBusinessDocumentdetail******************");
		Session session = currentSession();

		List<BusinessDocumentsDetailsBean> businessDocumentsDetailsBeanList = new ArrayList<BusinessDocumentsDetailsBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(BusinessDocumentsDetailsBean.class);

			if (businessDocumentsDetailsBean.getId() != null || businessDocumentsDetailsBean.getDocumentId() != null) {

				if (businessDocumentsDetailsBean.getId() != null) {
//					AES.setKey("");
//					AES.decrypt(businessDocumentsDetailsBean.getId());
					cr.add(Restrictions.eq("id", businessDocumentsDetailsBean.getId()));

				}

				if (businessDocumentsDetailsBean.getDocumentId() != null) {
					cr.add(Restrictions.eq("documentId", businessDocumentsDetailsBean.getDocumentId()));
				}

				businessDocumentsDetailsBeanList = cr.list();
			}

			logger.debug("businessDocumentsDetailsBeanList size=" + businessDocumentsDetailsBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getBusinessDocumentdetail******************");

		return businessDocumentsDetailsBeanList;
	}

	@Override
	public SuccessModel updateBusinessDocuments(BusinessDocumentsDetailsBean businessDocumentsDetailsBean) {
		logger.debug("***************start updateDoctorDocuments******************");
		BusinessDocumentsDetailsBean businessDocumentsDetailsBeanOld = new BusinessDocumentsDetailsBean();
		List<BusinessDocumentsDetailsBean> businessDocumentsDetailsBeanList = getBusinessDocumentdetail(
				businessDocumentsDetailsBean);

		if (businessDocumentsDetailsBeanList.size() == 0) {
			createBusinessDocumentsDetails(businessDocumentsDetailsBeanOld);
			businessDocumentsDetailsBeanList = getBusinessDocumentdetail(businessDocumentsDetailsBean);
		}
		Session session = currentSession();
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

			if (businessDocumentsDetailsBeanList.size() > 0) {
				businessDocumentsDetailsBeanOld = businessDocumentsDetailsBeanList.get(0);
				session.getTransaction().begin();

				if (businessDocumentsDetailsBean.getCompanyLogo() != null) {
					businessDocumentsDetailsBeanOld.setCompanyLogo(businessDocumentsDetailsBean.getCompanyLogo());
				}

				if (businessDocumentsDetailsBean.getQualiCertificate1() != null) {
					businessDocumentsDetailsBeanOld
							.setQualiCertificate1(businessDocumentsDetailsBean.getQualiCertificate1());
				}
				if (businessDocumentsDetailsBean.getQualiCertificate1() != null) {
					businessDocumentsDetailsBeanOld
							.setQualiCertificate1(businessDocumentsDetailsBean.getQualiCertificate1());
				}
				if (businessDocumentsDetailsBean.getQualiCertificate2() != null) {
					businessDocumentsDetailsBeanOld
							.setQualiCertificate2(businessDocumentsDetailsBean.getQualiCertificate2());
				}
				if (businessDocumentsDetailsBean.getQualiCertificate3() != null) {
					businessDocumentsDetailsBeanOld
							.setQualiCertificate3(businessDocumentsDetailsBean.getQualiCertificate3());
				}
				if (businessDocumentsDetailsBean.getQualiCertificate4() != null) {
					businessDocumentsDetailsBeanOld
							.setQualiCertificate4(businessDocumentsDetailsBean.getQualiCertificate4());
				}

				if (businessDocumentsDetailsBean.getQualiCertificate5() != null) {
					businessDocumentsDetailsBeanOld
							.setQualiCertificate5(businessDocumentsDetailsBean.getQualiCertificate5());
				}
				if (businessDocumentsDetailsBean.getQualiCertificate6() != null) {
					businessDocumentsDetailsBeanOld
							.setQualiCertificate6(businessDocumentsDetailsBean.getQualiCertificate6());
				}
				if (businessDocumentsDetailsBean.getMsmeCertificate() != null) {
					businessDocumentsDetailsBeanOld
							.setMsmeCertificate(businessDocumentsDetailsBean.getMsmeCertificate());
				}
				if (businessDocumentsDetailsBean.getProfCertificate() != null) {
					businessDocumentsDetailsBeanOld
							.setProfCertificate(businessDocumentsDetailsBean.getProfCertificate());
				}
				if (businessDocumentsDetailsBean.getIsoCertificate() != null) {
					businessDocumentsDetailsBeanOld.setIsoCertificate(businessDocumentsDetailsBean.getIsoCertificate());
				}
				if (businessDocumentsDetailsBean.getGstCertificate() != null) {
					businessDocumentsDetailsBeanOld.setGstCertificate(businessDocumentsDetailsBean.getGstCertificate());
				}
				if (businessDocumentsDetailsBean.getComplianceCertificate() != null) {
					businessDocumentsDetailsBeanOld
							.setComplianceCertificate(businessDocumentsDetailsBean.getComplianceCertificate());
				}
				if (businessDocumentsDetailsBean.getGumastaCertificate() != null) {
					businessDocumentsDetailsBeanOld
							.setGumastaCertificate(businessDocumentsDetailsBean.getGumastaCertificate());
				}
				if (businessDocumentsDetailsBean.getCompanyAadhar() != null) {
					businessDocumentsDetailsBeanOld.setCompanyAadhar(businessDocumentsDetailsBean.getCompanyAadhar());
				}
				
				if (businessDocumentsDetailsBean.getCompanyAadharBack() != null) {
					businessDocumentsDetailsBeanOld.setCompanyAadharBack(businessDocumentsDetailsBean.getCompanyAadharBack());
				}
				if (businessDocumentsDetailsBean.getCompanyPan() != null) {
					businessDocumentsDetailsBeanOld.setCompanyPan(businessDocumentsDetailsBean.getCompanyPan());
				}
				if (businessDocumentsDetailsBean.getOtherLicCertificate1() != null) {
					businessDocumentsDetailsBeanOld
							.setOtherLicCertificate1(businessDocumentsDetailsBean.getOtherLicCertificate1());
				}
				if (businessDocumentsDetailsBean.getOtherLicCertificate2() != null) {
					businessDocumentsDetailsBeanOld
							.setOtherLicCertificate2(businessDocumentsDetailsBean.getOtherLicCertificate2());
				}
				if (businessDocumentsDetailsBean.getOtherLicCertificate3() != null) {
					businessDocumentsDetailsBeanOld
							.setOtherLicCertificate3(businessDocumentsDetailsBean.getOtherLicCertificate3());
				}
				if (businessDocumentsDetailsBean.getProfessionalWorkimg1() != null) {
					businessDocumentsDetailsBeanOld
							.setProfessionalWorkimg1(businessDocumentsDetailsBean.getProfessionalWorkimg1());
				}

				if (businessDocumentsDetailsBean.getProfessionalWorkimg2() != null) {
					businessDocumentsDetailsBeanOld
							.setProfessionalWorkimg2(businessDocumentsDetailsBean.getProfessionalWorkimg2());
				}
				if (businessDocumentsDetailsBean.getProfessionalWorkimg3() != null) {
					businessDocumentsDetailsBeanOld
							.setProfessionalWorkimg3(businessDocumentsDetailsBean.getProfessionalWorkimg3());
				}

				if (businessDocumentsDetailsBean.getWebsiteLink() != null) {
					businessDocumentsDetailsBeanOld.setWebsiteLink(businessDocumentsDetailsBean.getWebsiteLink());
				}

				if (businessDocumentsDetailsBean.getInstagramLink() != null) {
					businessDocumentsDetailsBeanOld.setInstagramLink(businessDocumentsDetailsBean.getInstagramLink());
				}

				if (businessDocumentsDetailsBean.getYoutubeLink() != null) {
					businessDocumentsDetailsBeanOld.setYoutubeLink(businessDocumentsDetailsBean.getYoutubeLink());
				}

				if (businessDocumentsDetailsBean.getFacebookLink() != null) {
					businessDocumentsDetailsBeanOld.setFacebookLink(businessDocumentsDetailsBean.getFacebookLink());
				}

				if (businessDocumentsDetailsBean.getTwitterLink() != null) {
					businessDocumentsDetailsBeanOld.setTwitterLink(businessDocumentsDetailsBean.getTwitterLink());
				}

				if (businessDocumentsDetailsBean.getAboutCompany() != null) {
					businessDocumentsDetailsBeanOld.setAboutCompany(businessDocumentsDetailsBean.getAboutCompany());
				}

				if (businessDocumentsDetailsBean.getDetailAboutProduct() != null) {
					businessDocumentsDetailsBeanOld
							.setDetailAboutProduct(businessDocumentsDetailsBean.getDetailAboutProduct());
				}
				session.update(businessDocumentsDetailsBeanOld);
				session.getTransaction().commit();
				status = ApplicationConstant.STATUS_SUCCESS;
				successModel.setId(businessDocumentsDetailsBeanOld.getDocumentId());
			} else {
				status = ApplicationConstant.STATUS_FAILED;
			}

		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status updateDoctorDocuments save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end updateDoctorDocuments***************************");
		return successModel;

	}

	@Override
	public SuccessModel updateBusinessAccountDetails(BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("***************start updateDoctorDocuments******************");
		BusinessAccountDetailsBean businessAccountDetailsBeanOld = new BusinessAccountDetailsBean();
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = getBusinessAccdetail(
				businessAccountDetailsBean);

		Session session = currentSession();
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

			if (businessAccountDetailsBeanList.size() > 0) {
				businessAccountDetailsBeanOld = businessAccountDetailsBeanList.get(0);
				session.getTransaction().begin();

				if (businessAccountDetailsBean.getCompanyTitle() != null) {
					businessAccountDetailsBeanOld.setCompanyTitle(businessAccountDetailsBean.getCompanyTitle());
				}

				if (businessAccountDetailsBean.getCompanyName() != null) {
					businessAccountDetailsBeanOld.setCompanyName(businessAccountDetailsBean.getCompanyName());
				}
				if (businessAccountDetailsBean.getCompanyOwnerName() != null) {
					businessAccountDetailsBeanOld.setCompanyOwnerName(businessAccountDetailsBean.getCompanyOwnerName());
				}
				if (businessAccountDetailsBean.getOwnerGender() != null) {
					businessAccountDetailsBeanOld.setOwnerGender(businessAccountDetailsBean.getOwnerGender());
				}
				if (businessAccountDetailsBean.getRepresentativeName() != null) {
					businessAccountDetailsBeanOld
							.setRepresentativeName(businessAccountDetailsBean.getRepresentativeName());
				}
				if (businessAccountDetailsBean.getRepresentativeGender() != null) {
					businessAccountDetailsBeanOld
							.setRepresentativeGender(businessAccountDetailsBean.getRepresentativeGender());
				}

				if (businessAccountDetailsBean.getState() != null) {
					businessAccountDetailsBeanOld.setState(businessAccountDetailsBean.getState());
				}
				if (businessAccountDetailsBean.getCity() != null) {
					businessAccountDetailsBeanOld.setCity(businessAccountDetailsBean.getCity());
				}
				if (businessAccountDetailsBean.getContactNo() != null) {
					businessAccountDetailsBeanOld.setContactNo(businessAccountDetailsBean.getContactNo());
				}
				if (businessAccountDetailsBean.getEmailId() != null) {
					businessAccountDetailsBeanOld.setEmailId(businessAccountDetailsBean.getEmailId());
				}
				if (businessAccountDetailsBean.getExperiencEstablishment() != null) {
					businessAccountDetailsBeanOld
							.setExperiencEstablishment(businessAccountDetailsBean.getExperiencEstablishment());
				}
				if (businessAccountDetailsBean.getLandlineNo() != null) {
					businessAccountDetailsBeanOld.setLandlineNo(businessAccountDetailsBean.getLandlineNo());
				}
				if (businessAccountDetailsBean.getLandmark() != null) {
					businessAccountDetailsBeanOld.setLandmark(businessAccountDetailsBean.getLandmark());
				}
				if (businessAccountDetailsBean.getOtherContactNo() != null) {
					businessAccountDetailsBeanOld.setOtherContactNo(businessAccountDetailsBean.getOtherContactNo());
				}
				if (businessAccountDetailsBean.getOtherEmailId() != null) {
					businessAccountDetailsBeanOld.setOtherEmailId(businessAccountDetailsBean.getOtherEmailId());
				}
				if (businessAccountDetailsBean.getSector() != null) {
					businessAccountDetailsBeanOld.setSector(businessAccountDetailsBean.getSector());
				}
				if (businessAccountDetailsBean.getOwnerDetail() != null) {
					businessAccountDetailsBeanOld.setOwnerDetail(businessAccountDetailsBean.getOwnerDetail());
				}
				if (businessAccountDetailsBean.getRegistOfficeAdd() != null) {
					businessAccountDetailsBeanOld.setRegistOfficeAdd(businessAccountDetailsBean.getRegistOfficeAdd());
				}
				if (businessAccountDetailsBean.getZipCode() != null) {
					businessAccountDetailsBeanOld.setZipCode(businessAccountDetailsBean.getZipCode());
				}
				if (businessAccountDetailsBean.getWhatsappNo() != null) {
					businessAccountDetailsBeanOld.setWhatsappNo(businessAccountDetailsBean.getWhatsappNo());
				}

				if (businessAccountDetailsBean.getPassportNo() != null) {
					businessAccountDetailsBeanOld.setPassportNo(businessAccountDetailsBean.getPassportNo());
				}
				if (businessAccountDetailsBean.getAadharNo() != null) {
					businessAccountDetailsBeanOld.setAadharNo(businessAccountDetailsBean.getAadharNo());
				}

				if (businessAccountDetailsBean.getOwnerReligion() != null) {
					businessAccountDetailsBeanOld.setOwnerReligion(businessAccountDetailsBean.getOwnerReligion());
				}

				if (businessAccountDetailsBean.getOwnerNationality() != null) {
					businessAccountDetailsBeanOld.setOwnerNationality(businessAccountDetailsBean.getOwnerNationality());
				}
				if (businessAccountDetailsBean.getOwnerNameTitle() != null) {
					businessAccountDetailsBeanOld.setOwnerNameTitle(businessAccountDetailsBean.getOwnerNameTitle());
				}
				if (businessAccountDetailsBean.getRepresentativeNameTitle() != null) {
					businessAccountDetailsBeanOld
							.setRepresentativeNameTitle(businessAccountDetailsBean.getRepresentativeNameTitle());
				}
				if (businessAccountDetailsBean.getServiceStates() != null) {
					businessAccountDetailsBeanOld.setServiceStates(businessAccountDetailsBean.getServiceStates());
				}
				if (businessAccountDetailsBean.getServiceCities() != null) {
					businessAccountDetailsBeanOld.setServiceCities(businessAccountDetailsBean.getServiceCities());
				}
				session.update(businessAccountDetailsBeanOld);
				session.getTransaction().commit();
				status = ApplicationConstant.STATUS_SUCCESS;
				successModel.setId(businessAccountDetailsBean.getId());
			} else {
				status = ApplicationConstant.STATUS_FAILED;
			}

		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status updateDoctorDocuments save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end updateDoctorDocuments***************************");
		return successModel;

	}

	@Override
	public List<BusinessAccountDetailsBean> getBusinessAccdetail(
			BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("***************start getBusinessAccdetail******************");
		Session session = currentSession();
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = new ArrayList<BusinessAccountDetailsBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(BusinessAccountDetailsBean.class);
			if (businessAccountDetailsBean.getId() != null) {
//				AES.setKey("");
//				AES.decrypt(businessAccountDetailsBean.getId());
				cr.add(Restrictions.eq("id", businessAccountDetailsBean.getId()));
				businessAccountDetailsBeanList = cr.list();
			}
			logger.debug("businessAccountDetailsBeanList size=" + businessAccountDetailsBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getBusinessAccdetail******************");

		return businessAccountDetailsBeanList;
	}

	@Override
	public List<BusinessAccountDetailsBean> getBusinessAccLists(BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("***************start getBusinessAccLists******************");
		Session session = currentSession();

		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = new ArrayList<BusinessAccountDetailsBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(BusinessAccountDetailsBean.class);
			if (businessAccountDetailsBean.getId() != null) {
				cr.add(Restrictions.eq("id", businessAccountDetailsBean.getId()));

			}

			if (businessAccountDetailsBean.getEmailId() != null) {
				cr.add(Restrictions.eq("emailId", businessAccountDetailsBean.getEmailId()));

			}

			if (businessAccountDetailsBean.getCompanyName() != null) {
				cr.add(Restrictions.eq("companyName", businessAccountDetailsBean.getCompanyName()));

			}

			if (businessAccountDetailsBean.getContactNo() != null) {
				cr.add(Restrictions.eq("contactNo", businessAccountDetailsBean.getContactNo()));

			}
			cr.addOrder(Order.desc("createDate"));
			businessAccountDetailsBeanList = cr.list();

			logger.debug("businessAccountDetailsBeanList size=" + businessAccountDetailsBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getBusinessAccLists******************");

		return businessAccountDetailsBeanList;
	}

	@Override
	public SuccessModel businessLogin(AmpamtAccountBean ampamtAccountBean) {

		logger.debug("***************start businessLogin******************");
		logger.debug("ampamtAccountBean.getEmailId()="+ampamtAccountBean.getEmailId());
		SuccessModel successModel = new SuccessModel();
		Session session = currentSession();
		List<AmpamtAccountBean> ampamtAccountList = new ArrayList<AmpamtAccountBean>();
		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtAccountBean.class);
			Criterion emailId = Restrictions.eq("emailId", ampamtAccountBean.getEmailId().trim().toLowerCase());
			Criterion contactNo = Restrictions.eq("contactNo", ampamtAccountBean.getEmailId().trim().toLowerCase());
			LogicalExpression orExp = Restrictions.or(emailId, contactNo);
			cr.add(orExp);
//			cr.add(Restrictions.eq("username", ampamtAccountBean.getUsername()));
			cr.add(Restrictions.eq("accountType", ApplicationConstant.ACCOUNT_BUSINESS));
			AES.setKey("");
			AES.encrypt(ampamtAccountBean.getPassword());
			cr.add(Restrictions.eq("password", AES.getEncryptedString()));

			ampamtAccountList = cr.list();

			logger.debug("ampamtAccountList size=" + ampamtAccountList.size());
			successModel.setAccountType(ApplicationConstant.ACCOUNT_BUSINESS);
			if (!ampamtAccountList.isEmpty() && ampamtAccountList.size() > 0) {
				ampamtAccountBean = (AmpamtAccountBean) ampamtAccountList.get(0);
				if (ampamtAccountBean != null) {
//					AES.encrypt(ampamtAccountBean.getId());
					successModel.setId(ampamtAccountBean.getId());
					successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
					successModel.setActiveFlag(ampamtAccountBean.getActiveFlag());
					successModel.setEmailId(ampamtAccountBean.getEmailId());
					
				} else {
					successModel.setStatus(ApplicationConstant.STATUS_FAILED);
					logger.debug("invalid password");
				}

			} else {
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
				logger.debug("invalid password");
				successModel.setMsg("invalid login");
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		logger.debug("***************end businessLogin******************");
		return successModel;

	}

	@Override
	public SuccessModel changePassword(ChangePasswordModel changePasswordModel) {
		logger.debug("************************start changePassword***************************");
		SuccessModel successModel = new SuccessModel();
		String status = null;
		Session session = currentSession();
		StringBuilder stringBuilder = new StringBuilder();
		try {
			AES.setKey("");
			AES.encrypt(changePasswordModel.getPassword());

			session.getTransaction().begin();
			stringBuilder.append(" update ampamt_account set PASSWORD ='" + AES.getEncryptedString() + "'");
			stringBuilder.append(" where (EMAIL_ID='" + changePasswordModel.getEmailId().trim().toLowerCase()
					+ "' or CONTACT_NO='" + changePasswordModel.getEmailId().trim() + "')");
			stringBuilder.append(" and ACCOUNT_TYPE='" + changePasswordModel.getAccountType() + "'");
			logger.debug("sql=" + stringBuilder.toString());
			SQLQuery query = session.createSQLQuery(stringBuilder.toString());
			query.executeUpdate();

			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;
			successModel.setMsg(ApplicationConstant.FOUND);
		} catch (Exception e) {
			logger.debug("exception during changePassword>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status changePassword update=" + status);
		successModel.setStatus(status);
		logger.debug("************************end changePassword***************************");
		return successModel;
	}

	@Override
	public SuccessModel checkAmpamtAccount(AmpamtAccountBean ampamtAccountBean) {

		logger.debug("***************start checkAmpamtAccount******************");

		SuccessModel successModel = new SuccessModel();
		Session session = currentSession();
		List<AmpamtAccountBean> ampamtAccountList = new ArrayList<AmpamtAccountBean>();
		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtAccountBean.class);

			if (ampamtAccountBean.getEmailId() != null || ampamtAccountBean.getPassword() != null
					|| ampamtAccountBean.getContactNo() != null) {

				if (ampamtAccountBean.getEmailId() != null) {
					Criterion emailId = Restrictions.eq("emailId", ampamtAccountBean.getEmailId().trim().toLowerCase());
					Criterion contactNo = Restrictions.eq("contactNo",ampamtAccountBean.getEmailId().trim().toLowerCase());
					LogicalExpression orExp = Restrictions.or(emailId, contactNo);
					cr.add(orExp);
				}

				if (ampamtAccountBean.getUsername() != null) {
					cr.add(Restrictions.eq("username", ampamtAccountBean.getUsername()));
				}
				if (ampamtAccountBean.getContactNo() != null) {
					cr.add(Restrictions.eq("contactNo", ampamtAccountBean.getContactNo()));
				}

				if (ampamtAccountBean.getId() != null) {
					cr.add(Restrictions.eq("id", ampamtAccountBean.getId()));
				}
				cr.add(Restrictions.eq("accountType", ampamtAccountBean.getAccountType()));

				if (ampamtAccountBean.getPassword() != null) {
					AES.setKey("");
					AES.encrypt(ampamtAccountBean.getPassword());
					cr.add(Restrictions.eq("password", AES.getEncryptedString()));
				}

				ampamtAccountList = cr.list();
			}

			logger.debug("ampamtAccountList size=" + ampamtAccountList.size());

			if (!ampamtAccountList.isEmpty() && ampamtAccountList.size() > 0) {
				successModel.setAccountExists(ApplicationConstant.FLAG_Y);
				ampamtAccountBean = (AmpamtAccountBean) ampamtAccountList.get(0);
				if (ampamtAccountBean != null) {
//					AES.encrypt(ampamtAccountBean.getId());
					successModel.setId(ampamtAccountBean.getId());
					successModel.setEmailId(ampamtAccountBean.getEmailId());
					successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
					successModel.setMsg(ApplicationConstant.FOUND);
				} else {
					successModel.setStatus(ApplicationConstant.STATUS_FAILED);

					successModel.setMsg(ApplicationConstant.NOTFOUNT);
				}

			} else {
				successModel.setAccountExists(ApplicationConstant.FLAG_N);
				successModel.setMsg(ApplicationConstant.NOTFOUNT);
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
				logger.debug("account not found");

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		logger.debug("***************end checkAmpamtAccount******************");
		return successModel;

	}

	@Override
	public SuccessModel setOTP(AmpamtAccountBean ampamtAccountBean) {
		logger.debug("***************start setOTP******************");
		SuccessModel successModel = new SuccessModel();

		Session session = currentSession();
		StringBuilder stringBuilder = new StringBuilder();
		try {

			if(ampamtAccountBean.getOtp()!=null) {
				
				session.getTransaction().begin();
				stringBuilder.append(" update ampamt_account set OTP ='" + ampamtAccountBean.getOtp() + "'");
				stringBuilder.append(" where");
				stringBuilder.append(" ID='" + ampamtAccountBean.getId()+"'");
				if(ampamtAccountBean.getEmailId()!=null) {
					stringBuilder.append(" and (EMAIL_ID='" + ampamtAccountBean.getEmailId().trim().toLowerCase()+"'");
					stringBuilder.append( " or CONTACT_NO= '" + ampamtAccountBean.getEmailId().trim() + "')");
				}
				
				stringBuilder.append(" and ACCOUNT_TYPE='" + ampamtAccountBean.getAccountType() + "'");
				
				logger.debug("sql=" + stringBuilder.toString());
				SQLQuery query = session.createSQLQuery(stringBuilder.toString());
				query.executeUpdate();
	
				session.getTransaction().commit();
				successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
				successModel.setMsg(ApplicationConstant.FOUND);
			}
		} catch (Exception e) {
			logger.debug("exception during changePassword>>" + e);
			session.getTransaction().rollback();
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end setOTP******************");
		return successModel;
	}

	@Override
	public SuccessModel approveAccount(AmpamtAccountBean ampamtAccountBean) {
		logger.debug("***************start approveAccount******************");
		SuccessModel successModel = new SuccessModel();

		String status = null;
		Session session = currentSession();
		StringBuilder stringBuilder = new StringBuilder();
		try {

			session.getTransaction().begin();
			stringBuilder.append(" update ampamt_account set ACTIVE_FLAG ='" + ampamtAccountBean.getActiveFlag()+ "',OTP=null, STATUS='" + ampamtAccountBean.getStatus() + "' where");

			if (ampamtAccountBean.getEmailId() != null) {
				stringBuilder.append(" EMAIL_ID='" + ampamtAccountBean.getEmailId().trim().toLowerCase() + "' and");
			}
			stringBuilder.append(" ACCOUNT_TYPE='" + ampamtAccountBean.getAccountType() + "'");
			
			stringBuilder.append(" and ID='" + ampamtAccountBean.getId() + "'");
			logger.debug("sql=" + stringBuilder.toString());
			SQLQuery query = session.createSQLQuery(stringBuilder.toString());
			int result=query.executeUpdate();
			logger.debug("result="+result);
			session.getTransaction().commit();

			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			successModel.setId(ampamtAccountBean.getId());
			successModel.setActiveFlag(ApplicationConstant.FLAG_Y);

		} catch (Exception e) {
			logger.debug("exception during changePassword>>" + e);
			session.getTransaction().rollback();
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end approveAccount******************");
		return successModel;
	}

	@Override
	public SuccessModel getTotalUsers(AmpamtAccountBean ampamtAccountBean) {
		logger.debug("***************start getTotalUsers******************");
		SuccessModel successModel = new SuccessModel();
		List<SuccessModel> successModelList = new ArrayList<SuccessModel>();
		String status = null;
		Session session = currentSession();
		StringBuilder stringBuilder = new StringBuilder();
		try {

			session.getTransaction().begin();
			Criteria crit = session.createCriteria(AmpamtAccountBean.class);
			crit.add( Restrictions.isNotNull("id"));
			
			crit.setProjection(Projections.rowCount());
			Long count = (Long)crit.uniqueResult();
			logger.debug("count="+count);
			successModel.setCount(Integer.parseInt(count.toString()));

//			successModelList = session.createSQLQuery(stringBuilder.toString())
//					.setResultTransformer(new CustomizedResultTransformer(SuccessModel.class)).list();
			logger.debug("successModelList size=" + successModelList.size());

			if(successModelList.size()>0)
				successModel = successModelList.get(0);
			

			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			logger.debug("exception during changePassword>>" + e);
			session.getTransaction().rollback();
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}

		logger.debug("***************end getTotalUsers******************");
		return successModel;
	}

	@Override
	public List<BusinessAccountDetailsBean> searchServiceProviders(
			BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("***************start searchServiceProviders******************");
		Session session = currentSession();
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = new ArrayList<BusinessAccountDetailsBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(BusinessAccountDetailsBean.class);
			Criterion serviceCity = Restrictions.ilike("serviceCities",
					"%" + businessAccountDetailsBean.getServiceCities() + "%");
			Criterion serviceState = Restrictions.ilike("serviceStates",
					"%" + businessAccountDetailsBean.getServiceStates() + "%");

			Criterion serviceAllCity = Restrictions.ilike("serviceCities", "%" + ApplicationConstant.ALL_CITIES + "%");
			Criterion serviceAllState = Restrictions.ilike("serviceStates", "%" + ApplicationConstant.ALL_STATES + "%");

			LogicalExpression orExp = Restrictions.or(serviceCity, serviceAllCity);
			LogicalExpression orExp2 = Restrictions.or(serviceState, serviceAllState);
			cr.add(orExp);
			cr.add(orExp2);

			if (businessAccountDetailsBean.getSector() != null) {
				cr.add(Restrictions.ilike("sector", "%" + businessAccountDetailsBean.getSector() + "%"));
			}

			businessAccountDetailsBeanList = cr.list();

			logger.debug("businessAccountDetailsBeanList size=" + businessAccountDetailsBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end searchServiceProviders******************");

		return businessAccountDetailsBeanList;
	}

	@Override
	public List<UserSearchModel> searchUsers(UserSearchModel userSearchModel) {
		logger.debug("***************start searchUsers******************");

		List<UserSearchModel> userSearchModelList = new ArrayList<UserSearchModel>();
		String status = null;
		Session session = currentSession();
		StringBuilder stringBuilder = new StringBuilder();
		try {

			session.getTransaction().begin();
			stringBuilder.append(" SELECT");
			stringBuilder.append(
					" doctor.id id,doctor.account_type accountType,doctor.name_title nameTitle,doctor.first_name || ' '|| doctor.last_name name,doctor.gender, doctor.practice_city  city,'' state,'' zipCode,");
			stringBuilder.append(
					"  doctor.email_id emailId,doctor.contact_no contactNo,doctor.whatsapp_no whatsappNo, doctor.emergency_no emergencyNo FROM ampamt_account acc");
			stringBuilder.append(" JOIN doctors_account_details doctor ON acc.id = doctor.id ");
			stringBuilder.append(" WHERE acc.id = '" + userSearchModel.getId() + "'");
			stringBuilder.append(" UNION ALL");
			stringBuilder.append(
					" SELECT  business.id id,business.account_type accountType, business.owner_name_title nameTitle,business.company_owner_name name,business.owner_gender,business.city,business.state,");
			stringBuilder.append(
					" business.zip_code,business.email_id emailId,business.contact_no contactNo,business.whatsapp_no whatsappNo,'' emergencyNo");
			stringBuilder
					.append(" FROM ampamt_account acc JOIN business_account_details business ON acc.id = business.id");
			stringBuilder.append(" WHERE acc.id = '" + userSearchModel.getId() + "'");

			userSearchModelList = session.createSQLQuery(stringBuilder.toString())
					.setResultTransformer(new CustomizedResultTransformer(UserSearchModel.class)).list();
			logger.debug("userSearchModelList size=" + userSearchModelList.size());

		} catch (Exception e) {
			logger.debug("exception during searchUsers>>" + e);
			session.getTransaction().rollback();

			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}

		logger.debug("***************end searchUsers******************");
		return userSearchModelList;
	}

	@Override
	public List<DocumentsVerificationModel> documentsVerification(AmpamtAccountBean ampamtAccountBean) {
		logger.debug("***************start documentsVerification******************");
		Session session = currentSession();
		List<DocumentsVerificationModel> documentsVerificationModelList = new ArrayList<DocumentsVerificationModel>();
		try {

			session.getTransaction().begin();
			String sqlQuery = "WITH documents_verification AS (\r\n" + 
					"    SELECT\r\n" + 
					"        ampt_acc.id,\r\n" + 
					"        ampt_acc.account_type    accounttype,\r\n" + 
					"        CASE\r\n" + 
					"            WHEN ( doctor_doc.profile_pic IS NULL\r\n" + 
					"                   OR doctor_doc.profile_pic = 'N' )\r\n" + 
					
					"                 OR ( doctor_doc.aadhar IS NULL\r\n" + 
					"                      OR doctor_doc.aadhar = 'N' )\r\n" + 
					"                 OR ( doctor_doc.AADHAR_BACK IS NULL\r\n" + 
					"                      OR doctor_doc.AADHAR_BACK = 'N' )\r\n" + 
					"                 OR ( doctor_doc.panfront IS NULL\r\n" + 
					"                      OR doctor_doc.panfront = 'N' )\r\n" + 
					
					"                 OR ( doctor_doc.quali_certificate1 IS NULL\r\n" + 
					"                      OR doctor_doc.quali_certificate1 = 'N' )\r\n" + 
					"                 OR ( doctor_doc.professional_workimg1 IS NULL\r\n" + 
					"                      OR doctor_doc.professional_workimg1 = 'N' )\r\n" + 
					"                 OR ( doctor_doc.professional_workimg2 IS NULL\r\n" + 
					"                      OR doctor_doc.professional_workimg2 = 'N' )\r\n" + 
					"                 OR ( doctor_doc.signature_img IS NULL\r\n" + 
					"                      OR doctor_doc.signature_img = 'N' )\r\n" + 
					"                 OR ( doctor_doc.other_lic_certificate1 IS NULL\r\n" + 
					"                      OR doctor_doc.other_lic_certificate1 = 'N' )\r\n" + 
					"                 OR ( doctor_doc.professional_workimg3 IS NULL\r\n" + 
					"                      OR doctor_doc.professional_workimg3 = 'N' ) THEN\r\n" + 
					"                'N'\r\n" + 
					"            ELSE\r\n" + 
					"                'Y'\r\n" + 
					"        END                      AS alldocuploaded,\r\n" + 
					"        'NA'                     isLocationSelected\r\n" + 
					"    FROM\r\n" + 
					"             ampamt_account ampt_acc\r\n" + 
					"        JOIN doctors_documents_details doctor_doc ON ampt_acc.id = doctor_doc.id\r\n" + 
					"    UNION ALL\r\n" + 
					"    SELECT\r\n" + 
					"        ampt_acc.id,\r\n" + 
					"        ampt_acc.account_type    accounttype,\r\n" + 
					"        CASE\r\n" + 
					"            WHEN ( business_doc.company_logo IS NULL\r\n" + 
					"                   OR business_doc.company_logo = 'N' ) THEN\r\n" + 
					"                'N'\r\n" + 
					"            ELSE\r\n" + 
					"                'Y'\r\n" + 
					"        END                      AS alldocuploaded,\r\n" + 
					"        CASE\r\n" + 
					"            WHEN ( business_acc.service_states IS NULL\r\n" + 
					"                   OR business_acc.service_cities IS NULL ) THEN\r\n" + 
					"                'N'\r\n" + 
					"            ELSE\r\n" + 
					"                'Y'\r\n" + 
					"        END                      AS isLocationSelected\r\n" + 
					"    FROM\r\n" + 
					"             ampamt_account ampt_acc\r\n" + 
					"        JOIN business_documents_details  business_doc ON business_doc.id = ampt_acc.id\r\n" + 
					"        JOIN business_account_details    business_acc ON business_acc.id = ampt_acc.id\r\n" + 
					"), cash_account AS (\r\n" + 
					"    SELECT\r\n" + 
					"        cash_acc.account_id,\r\n" + 
					"        SUM(cash_acc.available_amount) AS available_amount\r\n" + 
					"    FROM\r\n" + 
					"             ampamt_account ampt_acc\r\n" + 
					"        JOIN ampamt_cash_account cash_acc ON ampt_acc.id = cash_acc.account_id\r\n" + 

					"    GROUP BY\r\n" + 
					"        cash_acc.account_id\r\n" + 
					")\r\n" + 
					"SELECT\r\n" + 
					"    documents.*,\r\n" + 
					"    CASE\r\n" + 
					"        WHEN cacc.available_amount IS NULL THEN\r\n" + 
					"            '0'\r\n" + 
					"        ELSE\r\n" + 
					"            CONVERT(cacc.available_amount,char)\r\n" + 
					"    END AS availableamount\r\n" + 
					"FROM\r\n" + 
					"    documents_verification  documents\r\n" + 
					"    LEFT JOIN cash_account            cacc ON documents.id = cacc.account_id\r\n" + 
					"WHERE\r\n" + 
					"    documents.id = '"+ampamtAccountBean.getId()+"'";
			documentsVerificationModelList = session.createSQLQuery(sqlQuery).setResultTransformer(new CustomizedResultTransformer(DocumentsVerificationModel.class)).list();
			logger.debug("documentsVerificationModelList size=" + documentsVerificationModelList.size());

		} catch (Exception e) {
			logger.debug("exception during searchUsers>>" + e);
			

			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}

		logger.debug("***************end documentsVerification******************");
		return documentsVerificationModelList;
	}

	@Override
	public SuccessModel approveDoctorAccount(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("***************start approveAccount******************");
		SuccessModel successModel = new SuccessModel();

		String status = null;
		Session session = currentSession();
		StringBuilder stringBuilder = new StringBuilder();
		try {

			session.getTransaction().begin();
			stringBuilder.append(" update doctors_account_details set ACTIVE_FLAG ='" + doctorsAccountDetailsBean.getActiveFlag()
					+ "', STATUS='" + doctorsAccountDetailsBean.getStatus() + "' where");

			if (doctorsAccountDetailsBean.getEmailId() != null) {
				stringBuilder.append(" EMAIL_ID='" + doctorsAccountDetailsBean.getEmailId().trim().toLowerCase() + "' and");
			}

			
			stringBuilder.append(" ACCOUNT_TYPE='" + doctorsAccountDetailsBean.getAccountType() + "'");
//			AES.setKey("");
//			AES.decrypt(ampamtAccountBean.getId());

			stringBuilder.append(" and ID='" + doctorsAccountDetailsBean.getId() + "'");
			logger.debug("sql=" + stringBuilder.toString());
			SQLQuery query = session.createSQLQuery(stringBuilder.toString());
			query.executeUpdate();

			session.getTransaction().commit();

			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			successModel.setId(doctorsAccountDetailsBean.getId());
			successModel.setActiveFlag(ApplicationConstant.FLAG_Y);

		} catch (Exception e) {
			logger.debug("exception during changePassword>>" + e);
			session.getTransaction().rollback();
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end approveAccount******************");
		return successModel;
	}

	@Override
	public SuccessModel approveBusinessAccount(BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("***************start approveAccount******************");
		SuccessModel successModel = new SuccessModel();

		String status = null;
		Session session = currentSession();
		StringBuilder stringBuilder = new StringBuilder();
		try {

			session.getTransaction().begin();
			stringBuilder.append(" update business_account_details set ACTIVE_FLAG ='" + businessAccountDetailsBean.getActiveFlag()
					+ "', STATUS='" + businessAccountDetailsBean.getStatus() + "' where");

			if (businessAccountDetailsBean.getEmailId() != null) {
				stringBuilder.append(" EMAIL_ID='" + businessAccountDetailsBean.getEmailId().trim().toLowerCase() + "' and");
			}

			
			stringBuilder.append(" ACCOUNT_TYPE='" + businessAccountDetailsBean.getAccountType() + "'");
//			AES.setKey("");
//			AES.decrypt(ampamtAccountBean.getId());

			stringBuilder.append(" and ID='" + businessAccountDetailsBean.getId() + "'");
			logger.debug("sql=" + stringBuilder.toString());
			SQLQuery query = session.createSQLQuery(stringBuilder.toString());
			query.executeUpdate();

			session.getTransaction().commit();

			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			successModel.setId(businessAccountDetailsBean.getId());
			successModel.setActiveFlag(ApplicationConstant.FLAG_Y);

		} catch (Exception e) {
			logger.debug("exception during changePassword>>" + e);
			session.getTransaction().rollback();
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end approveAccount******************");
		return successModel;
	}

	@Override
	public List<AmpamtTherapiesBean> getTherapyLists(AmpamtTherapiesBean ampamtTherapiesBean) {
		logger.debug("***************start getTherapyLists******************");
		Session session = currentSession();
		List<AmpamtTherapiesBean> ampamtTherapiesBeanList = new ArrayList<AmpamtTherapiesBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtTherapiesBean.class);
			if (ampamtTherapiesBean.getTherapyId() != null) {
				cr.add(Restrictions.eq("therapyId", ampamtTherapiesBean.getTherapyId()));
			}
			if (ampamtTherapiesBean.getTherapyName() != null) {
				cr.add(Restrictions.eq("therapyName", ampamtTherapiesBean.getTherapyName()));
			}
			
			cr.add(Restrictions.eq("activeFlag", ApplicationConstant.FLAG_Y));
			cr.addOrder(Order.asc("therapyName"));
			ampamtTherapiesBeanList = cr.list();
			logger.debug("ampamtTherapiesBeanList size=" + ampamtTherapiesBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getTherapyLists******************");

		return ampamtTherapiesBeanList;
	}

	@Override
	public SuccessModel createAuditTrail(String clientIp, String accountid, String action, String module) {
		logger.debug("************************start createAuditTrail***************************");
		SuccessModel successModel = new SuccessModel();

		String auditId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
		auditId="ADT"+auditId;
		String status = null;
		Session session = currentSession();
		StringBuilder stringBuilder = new StringBuilder();
		try {
			
			session.getTransaction().begin();
			stringBuilder.append(" Insert into ampamt_audit_trail (ID,ACCOUNT_ID,ACTION,SERVER_IP,CLIENT_IP,AUDIT_DATE,MODULE)");
			stringBuilder.append(" values ('"+auditId+"','"+accountid+"','"+action+"','153.92.214.113','"+clientIp+"',SYSDATE(),'"+module+"')");

			logger.debug("sql=" + stringBuilder.toString());
			SQLQuery query = session.createSQLQuery(stringBuilder.toString());
			query.executeUpdate();

			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;
			successModel.setMsg(ApplicationConstant.FOUND);
		} catch (Exception e) {
			logger.debug("exception during createAuditTrail>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status createAuditTrail update=" + status);
		successModel.setStatus(status);
		logger.debug("************************end createAuditTrail***************************");
		return successModel;
	}

	@Override
	public SuccessModel verifyOtp(AmpamtAccountBean ampamtAccountBean) {

		logger.debug("***************start verifyOtp******************");

		SuccessModel successModel = new SuccessModel();
		Session session = currentSession();
		List<AmpamtAccountBean> ampamtAccountList = new ArrayList<AmpamtAccountBean>();
		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtAccountBean.class);
			if(ampamtAccountBean.getEmailId()!=null) {
				Criterion emailId = Restrictions.eq("emailId", ampamtAccountBean.getEmailId().trim().toLowerCase());
				Criterion contactNo = Restrictions.eq("contactNo", ampamtAccountBean.getEmailId().trim().toLowerCase());
				LogicalExpression orExp = Restrictions.or(emailId, contactNo);
				cr.add(orExp);
			}
			
			cr.add(Restrictions.eq("id", ampamtAccountBean.getId()));
			cr.add(Restrictions.eq("otp", ampamtAccountBean.getOtp()));
			cr.add(Restrictions.eq("accountType", ampamtAccountBean.getAccountType()));
			
			ampamtAccountList = cr.list();

			logger.debug("ampamtAccountList size=" + ampamtAccountList.size());
			successModel.setStatusType(ApplicationConstant.OTP);
			if (!ampamtAccountList.isEmpty() && ampamtAccountList.size() > 0) {
				ampamtAccountBean = (AmpamtAccountBean) ampamtAccountList.get(0);
				if (ampamtAccountBean != null) {
//					AES.encrypt(ampamtAccount.getId());
					successModel.setId(ampamtAccountBean.getId());
					successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
					successModel.setActiveFlag(ampamtAccountBean.getActiveFlag());
					successModel.setEmailId(ampamtAccountBean.getEmailId());
					successModel.setAccountType(ampamtAccountBean.getAccountType());
					successModel.setStatusType(ApplicationConstant.OTP);
				} else {
					successModel.setStatus(ApplicationConstant.STATUS_FAILED);
					
					logger.debug("invalid verifyOtp");
				}

			} else {
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
				
				logger.debug("invalid verifyOtp");
				successModel.setMsg("invalid verifyOtp");
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		logger.debug("***************end verifyOtp******************");
		return successModel;

	}

	@Override
	public SuccessModel updateAmpamtAccount(AmpamtAccountBean ampamtAccountBean) {
		logger.debug("***************start updateAmpamtAccount******************");

		SuccessModel successModel = new SuccessModel();
		
		List<AmpamtAccountBean> ampamtAccountList= getAmpamtAccountById(ampamtAccountBean);
		Session session = currentSession();
		AmpamtAccountBean ampamtAccountBeanOld=new AmpamtAccountBean();
		try {

			if (ampamtAccountList.size() > 0) {
				ampamtAccountBeanOld = ampamtAccountList.get(0);
				session.getTransaction().begin();

				if (ampamtAccountBean.getEmailId()!= null) {
					ampamtAccountBeanOld.setEmailId(ampamtAccountBean.getEmailId().trim().toLowerCase());
					ampamtAccountBeanOld.setUsername(ampamtAccountBean.getEmailId().trim().toLowerCase());
				}
				
				if(ampamtAccountBeanOld.getEmailId()!=null && ampamtAccountBean.getEmailId()!= null) {
					if(!ampamtAccountBeanOld.getEmailId().trim().toLowerCase().equalsIgnoreCase(ampamtAccountBean.getEmailId().trim().toLowerCase())) {
						ampamtAccountBeanOld.setActiveFlag(ApplicationConstant.FLAG_N);
						ampamtAccountBeanOld.setStatus(ApplicationConstant.STATUS_PENDING);
					}
				}
				
				if (ampamtAccountBean.getContactNo()!= null) {
					ampamtAccountBeanOld.setContactNo(ampamtAccountBean.getContactNo());
				}
				

				session.update(ampamtAccountBeanOld);
				session.getTransaction().commit();
				successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
				successModel.setId(ampamtAccountBeanOld.getId());
			} else {
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			}

		} catch (Exception e) {
			logger.debug("exception during update>>" + e);
			session.getTransaction().rollback();
			successModel.setStatus( ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end updateAmpamtAccount******************");
		return successModel;
	}

	@Override
	public List<AmpamtAccountBean> getAmpamtAccountById(AmpamtAccountBean ampamtAccountBean) {

		logger.debug("***************start getAmpamtAccountById******************");

		SuccessModel successModel = new SuccessModel();
		Session session = currentSession();
		List<AmpamtAccountBean> ampamtAccountList = new ArrayList<AmpamtAccountBean>();
		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtAccountBean.class);
			if(ampamtAccountBean.getId()!=null) {
				cr.add(Restrictions.eq("id", ampamtAccountBean.getId()));
				
				ampamtAccountList = cr.list();
			}
			
//			cr.add(Restrictions.eq("accountType", ApplicationConstant.ACCOUNT_BUSINESS));


			logger.debug("ampamtAccountList size=" + ampamtAccountList.size());
			successModel.setAccountType(ApplicationConstant.ACCOUNT_BUSINESS);
			if (!ampamtAccountList.isEmpty() && ampamtAccountList.size() > 0) {
				ampamtAccountBean = (AmpamtAccountBean) ampamtAccountList.get(0);
				if (ampamtAccountBean != null) {
//					AES.encrypt(ampamtAccountBean.getId());
					successModel.setId(ampamtAccountBean.getId());
					successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
					successModel.setActiveFlag(ampamtAccountBean.getActiveFlag());
					successModel.setEmailId(ampamtAccountBean.getEmailId());
					
				} else {
					successModel.setStatus(ApplicationConstant.STATUS_FAILED);
					
				}

			} else {
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
				
				successModel.setMsg(ApplicationConstant.NOTFOUNT);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		logger.debug("***************end getAmpamtAccountById******************");
		return ampamtAccountList;

	}

	@Override
	public List<AmpamtAccountBean> getAmpamtAccountDetail(AmpamtAccountBean ampamtAccountBean) {

		logger.debug("***************start getAmpamtAccountDetail******************");

		Session session = currentSession();
		List<AmpamtAccountBean> ampamtAccountList = new ArrayList<AmpamtAccountBean>();
		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtAccountBean.class);

			if ((ampamtAccountBean.getEmailId() != null && ampamtAccountBean.getContactNo() != null) || ampamtAccountBean.getId() != null) {

				if (ampamtAccountBean.getEmailId() != null) {
					Criterion emailId = Restrictions.eq("emailId", ampamtAccountBean.getEmailId().trim().toLowerCase());
					Criterion contactNo = Restrictions.eq("contactNo",ampamtAccountBean.getContactNo());
					LogicalExpression orExp = Restrictions.or(emailId, contactNo);
					cr.add(orExp);
				}

				if (ampamtAccountBean.getId() != null) {
					cr.add(Restrictions.eq("id", ampamtAccountBean.getId()));
				}
				
				cr.add(Restrictions.eq("accountType", ampamtAccountBean.getAccountType()));
				ampamtAccountList = cr.list();
			}

			logger.debug("ampamtAccountList size=" + ampamtAccountList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		logger.debug("***************end getAmpamtAccountDetail******************");
		return ampamtAccountList;

	}

	@Override
	public List<DoctorAccountStatusModel> getDoctorsAccountListByStatus(DoctorAccountStatusModel doctorAccountStatusModel) {
		logger.debug("***************start getDoctorsAccountListByStatus******************");
		
		List<DoctorAccountStatusModel> doctorAccountStatusModelList=new ArrayList<DoctorAccountStatusModel>();
		Session session = currentSession();
		try {

			session.getTransaction().begin();
			String sqlQuery = "WITH doctor_account_status AS (\r\n" + 
					"    SELECT\r\n" + 
					"        doc_acc.name_title,\r\n" + 
					"        doc_acc.first_name,\r\n" + 
					"        doc_acc.last_name,\r\n" + 
					"        doc_acc.father_name,\r\n" + 
					"        account.email_id,\r\n" + 
					"        account.id,\r\n" + 
					"        account.account_type,\r\n" + 
					"        doc_acc.emergency_no,\r\n" + 
					"        doc_acc.contact_no,\r\n" + 
					"        doc_acc.whatsapp_no,\r\n" + 
					"        doc_acc.other_contact_no,\r\n" + 
					"        doc_acc.create_date,\r\n" + 
					
					"        account.status    account_status,\r\n" + 
					"        CASE\r\n" + 
					"            WHEN cashaccount.available_amount = 0\r\n" + 
					"                 OR cashaccount.available_amount IS NULL THEN\r\n" + 
					"                'N'\r\n" + 
					"            ELSE\r\n" + 
					"                'Y'\r\n" + 
					"        END               AS paid_flag,\r\n" + 
					"        CASE\r\n" + 
					"            WHEN ( doctor_doc.profile_pic IS NULL\r\n" + 
					"                   OR doctor_doc.profile_pic = 'N' )\r\n" + 
					"                 OR ( doctor_doc.aadhar IS NULL\r\n" + 
					"                      OR doctor_doc.aadhar = 'N' )\r\n" + 
					"                 OR ( doctor_doc.aadhar_back IS NULL\r\n" + 
					"                      OR doctor_doc.aadhar_back = 'N' )\r\n" + 
					"                 OR ( doctor_doc.panfront IS NULL\r\n" + 
					"                      OR doctor_doc.panfront = 'N' )\r\n" + 
					"                 OR ( doctor_doc.quali_certificate1 IS NULL\r\n" + 
					"                      OR doctor_doc.quali_certificate1 = 'N' )\r\n" + 
					"                 OR ( doctor_doc.professional_workimg1 IS NULL\r\n" + 
					"                      OR doctor_doc.professional_workimg1 = 'N' )\r\n" + 
					"                 OR ( doctor_doc.professional_workimg2 IS NULL\r\n" + 
					"                      OR doctor_doc.professional_workimg2 = 'N' )\r\n" + 
					"                 OR ( doctor_doc.signature_img IS NULL\r\n" + 
					"                      OR doctor_doc.signature_img = 'N' )\r\n" + 
					"                 OR ( doctor_doc.other_lic_certificate1 IS NULL\r\n" + 
					"                      OR doctor_doc.other_lic_certificate1 = 'N' )\r\n" + 
					"                 OR ( doctor_doc.professional_workimg3 IS NULL\r\n" + 
					"                      OR doctor_doc.professional_workimg3 = 'N' ) THEN\r\n" + 
					"                'N'\r\n" + 
					"            ELSE\r\n" + 
					"                'Y'\r\n" + 
					"        END               AS alldocuploaded,\r\n" + 
					"        account.active_flag\r\n" + 
					"    FROM\r\n" + 
					"        ampamt_account             account\r\n" + 
					"        LEFT JOIN ampamt_cash_account        cashaccount ON account.id = cashaccount.account_id\r\n" + 
					"        JOIN doctors_account_details    doc_acc ON account.id = doc_acc.id\r\n" + 
					"        JOIN doctors_documents_details  doctor_doc ON account.id = doctor_doc.id\r\n" + 
					"    WHERE\r\n" + 
					"        account.account_type = 'DOCTOR'\r\n" + 
					"    ORDER BY\r\n" + 
					"        create_date DESC,\r\n" + 
					"        paid_flag DESC,\r\n" + 
					"        alldocuploaded DESC,\r\n" + 
					"        active_flag DESC\r\n" + 
					")\r\n" + 
					"SELECT\r\n" + 
					"    doctoracc.*,\r\n" + 
					"    CASE\r\n" + 
					"        WHEN paid_flag = 'N'         THEN\r\n" + 
					"            'step1'\r\n" + 
					"        WHEN alldocuploaded = 'N'    THEN\r\n" + 
					"            'step2'\r\n" + 
					"        WHEN active_flag = 'N'       THEN\r\n" + 
					"            'step3'\r\n" + 
					"        ELSE\r\n" + 
					"            'step4'\r\n" + 
					"    END AS step_completed\r\n" + 
					"FROM\r\n" + 
					"    doctor_account_status doctoracc " ;
			
			doctorAccountStatusModelList = session.createSQLQuery(sqlQuery).setResultTransformer(new CustomizedResultTransformer(DoctorAccountStatusModel.class)).list();
			logger.debug("doctorAccountStatusModelList size=" + doctorAccountStatusModelList.size());

		} catch (Exception e) {
			logger.debug("exception during doctorAccountStatusModelList>>" + e);
			

			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getDoctorsAccountListByStatus******************");
		return doctorAccountStatusModelList;
	}
	
	@Override
	public SuccessModel createAuditTrailByBean(AmpamtAuditTrailBean ampamtAuditTrailBean) {
		logger.debug("***************start createAuditTrailByBean******************");

		String auditId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
		auditId="ADT"+auditId;
		logger.debug("auditId=" + auditId);
		Session session = currentSession();
		
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

			session.getTransaction().begin();
			ampamtAuditTrailBean.setId(auditId);
			
			status = (String) session.save(ampamtAuditTrailBean);
			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;

			successModel.setId(auditId);
		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status createAuditTrailByBean save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end createAuditTrailByBean***************************");
		return successModel;

	}

}
