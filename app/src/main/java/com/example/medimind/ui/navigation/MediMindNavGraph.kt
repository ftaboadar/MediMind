package com.example.medimind.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.medimind.ui.screens.AddMedicationStep1Screen
import com.example.medimind.ui.screens.AddMedicationStep2Screen
import com.example.medimind.ui.screens.AddMedicationStep3Screen
import com.example.medimind.ui.screens.HomeScreen
import com.example.medimind.ui.screens.LoginScreen
import com.example.medimind.ui.screens.MedicationDetailScreen
import com.example.medimind.ui.screens.OnboardingScreen1
import com.example.medimind.ui.screens.OnboardingScreen2
import com.example.medimind.ui.screens.OnboardingScreen3
import com.example.medimind.ui.screens.RegisterScreen
import com.example.medimind.ui.screens.SetupProfileScreen
import com.example.medimind.ui.screens.SplashScreen
import com.example.medimind.ui.screens.TutorialScreen
import com.example.medimind.ui.screens.VerifyEmailScreen
import com.example.medimind.ui.viewmodel.AddMedicationViewModel

object Routes {
    const val SPLASH = "splash"
    const val ONBOARDING1 = "onboarding1"
    const val ONBOARDING2 = "onboarding2"
    const val ONBOARDING3 = "onboarding3"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val VERIFY_EMAIL = "verify_email"
    const val SETUP_PROFILE = "setup_profile"
    const val TUTORIAL = "tutorial"
    const val HOME = "home"
    const val GUIDE = "guide"
    const val STEP1 = "step1"
    const val STEP2 = "step2"
    const val STEP3 = "step3"
    const val SUCCESS = "success"
    const val DETAIL = "detail/{medicationId}"

    /** Builds a concrete DETAIL route string for a given [medicationId]. */
    fun detail(medicationId: String) = "detail/$medicationId"
}

@Composable
fun MediMindNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {

        // ------------------------------------------------------------------
        // Splash
        // ------------------------------------------------------------------
        composable(Routes.SPLASH) {
            SplashScreen(
                onFinish = { navController.navigate(Routes.ONBOARDING1) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }}
            )
        }

        // ------------------------------------------------------------------
        // Onboarding
        // ------------------------------------------------------------------
        composable(Routes.ONBOARDING1) {
            OnboardingScreen1(
                onNext = { navController.navigate(Routes.ONBOARDING2) },
                onSkip = { navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.ONBOARDING1) { inclusive = true }
                }}
            )
        }

        composable(Routes.ONBOARDING2) {
            OnboardingScreen2(
                onNext = { navController.navigate(Routes.ONBOARDING3) },
                onSkip = { navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.ONBOARDING1) { inclusive = true }
                }}
            )
        }

        composable(Routes.ONBOARDING3) {
            OnboardingScreen3(
                onNext = { navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.ONBOARDING1) { inclusive = true }
                }},
                onSkip = { navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.ONBOARDING1) { inclusive = true }
                }}
            )
        }

        // ------------------------------------------------------------------
        // Auth
        // ------------------------------------------------------------------
        composable(Routes.LOGIN) {
            LoginScreen(
                onLogin = { navController.navigate(Routes.HOME) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                }},
                onRegister = { navController.navigate(Routes.REGISTER) }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterEmpty = { navController.navigate(Routes.VERIFY_EMAIL) },
                onRegisterFilled = {
                    navController.navigate(Routes.SETUP_PROFILE)
                },
                onLogin = { navController.popBackStack() }
            )
        }

        composable(Routes.VERIFY_EMAIL) {
            VerifyEmailScreen(
                onResend = {},
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.SETUP_PROFILE) {
            SetupProfileScreen(
                onSave = { navController.navigate(Routes.TUTORIAL) {
                    popUpTo(Routes.VERIFY_EMAIL) { inclusive = true }
                }},
                onSkip = { navController.navigate(Routes.TUTORIAL) {
                    popUpTo(Routes.VERIFY_EMAIL) { inclusive = true }
                }}
            )
        }

        composable(Routes.TUTORIAL) {
            TutorialScreen(
                onStart = { navController.navigate(Routes.HOME) {
                    popUpTo(Routes.TUTORIAL) { inclusive = true }
                }},
                onSkip = { navController.navigate(Routes.HOME) {
                    popUpTo(Routes.TUTORIAL) { inclusive = true }
                }}
            )
        }

        // ------------------------------------------------------------------
        // Home — ViewModel scoped here so wizard steps share the same instance
        // ------------------------------------------------------------------
        composable(Routes.HOME) { homeEntry ->
            val addMedicationViewModel: AddMedicationViewModel = viewModel(homeEntry)
            HomeScreen(
                viewModel = addMedicationViewModel,
                onManualEntry = { navController.navigate(Routes.STEP1) },
                onMedicationDetail = { id -> navController.navigate(Routes.detail(id)) }
            )
        }

        // ------------------------------------------------------------------
        // Medication detail
        // ------------------------------------------------------------------
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("medicationId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val medicationId = backStackEntry.arguments?.getString("medicationId") ?: ""
            MedicationDetailScreen(
                medicationId = medicationId,
                onBack = { navController.popBackStack() },
                onEdit = { navController.navigate(Routes.STEP1) }
            )
        }

        // ------------------------------------------------------------------
        // Add medication wizard — ViewModel scoped to HOME back-stack entry
        // ------------------------------------------------------------------
        composable(Routes.STEP1) {
            val homeEntry = remember(it) {
                navController.getBackStackEntry(Routes.HOME)
            }
            val addMedicationViewModel: AddMedicationViewModel = viewModel(homeEntry)
            AddMedicationStep1Screen(
                viewModel = addMedicationViewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Routes.STEP2) }
            )
        }

        composable(Routes.STEP2) {
            val homeEntry = remember(it) {
                navController.getBackStackEntry(Routes.HOME)
            }
            val addMedicationViewModel: AddMedicationViewModel = viewModel(homeEntry)
            AddMedicationStep2Screen(
                viewModel = addMedicationViewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Routes.STEP3) }
            )
        }

        composable(Routes.STEP3) {
            val homeEntry = remember(it) {
                navController.getBackStackEntry(Routes.HOME)
            }
            val addMedicationViewModel: AddMedicationViewModel = viewModel(homeEntry)
            AddMedicationStep3Screen(
                viewModel = addMedicationViewModel,
                onBack = { navController.popBackStack() },
                onSave = {
                    addMedicationViewModel.markSaved()
                    navController.popBackStack(Routes.HOME, inclusive = false)
                },
                onEdit = { navController.navigate(Routes.STEP2) }
            )
        }
    }
}
