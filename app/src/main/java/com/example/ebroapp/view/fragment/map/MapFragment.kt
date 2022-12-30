package com.example.ebroapp.view.fragment.map

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ebroapp.databinding.FragmentMapBinding
import com.example.ebroapp.view.base.BaseFragment

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(MapViewModel::class.java) {

//    private val mapboxReplayer = MapboxReplayer()
//    private val replayLocationEngine = ReplayLocationEngine(mapboxReplayer)
//    private val replayProgressObserver = ReplayProgressObserver(mapboxReplayer)
//    private lateinit var mapboxMap: MapboxMap
//    private lateinit var mapboxNavigation: MapboxNavigation
//    private var navigationCamera: NavigationCamera? = null
//    private lateinit var viewportDataSource: MapboxNavigationViewportDataSource
//
//    private lateinit var maneuverApi: MapboxManeuverApi
//    private lateinit var tripProgressApi: MapboxTripProgressApi
//    private lateinit var routeLineApi: MapboxRouteLineApi
//    private lateinit var routeLineView: MapboxRouteLineView
//    private val routeArrowApi: MapboxRouteArrowApi = MapboxRouteArrowApi()
//    private lateinit var routeArrowView: MapboxRouteArrowView
//    private var isVoiceInstructionsMuted = false
//        set(value) {
//            field = value
//            if (value) {
//                binding.soundButton.muteAndExtend(BUTTON_ANIMATION_DURATION)
//                voiceInstructionsPlayer.volume(SpeechVolume(0f))
//            } else {
//                binding.soundButton.unmuteAndExtend(BUTTON_ANIMATION_DURATION)
//                voiceInstructionsPlayer.volume(SpeechVolume(1f))
//            }
//        }
//    private lateinit var speechApi: MapboxSpeechApi
//    private lateinit var voiceInstructionsPlayer: MapboxVoiceInstructionsPlayer
//    private val voiceInstructionsObserver = VoiceInstructionsObserver { voiceInstructions ->
//        speechApi.generate(voiceInstructions, speechCallback)
//    }
//    private val speechCallback =
//        MapboxNavigationConsumer<Expected<SpeechError, SpeechValue>> { expected ->
//            expected.fold(
//                { error ->
//                    voiceInstructionsPlayer.play(
//                        error.fallback,
//                        voiceInstructionsPlayerCallback
//                    )
//                },
//                { value ->
//                    voiceInstructionsPlayer.play(
//                        value.announcement,
//                        voiceInstructionsPlayerCallback
//                    )
//                }
//            )
//        }
//    private val voiceInstructionsPlayerCallback =
//        MapboxNavigationConsumer<SpeechAnnouncement> { value ->
//            speechApi.clean(value)
//        }
//    private val navigationLocationProvider = NavigationLocationProvider()
//    private val locationObserver = object : LocationObserver {
//        var firstLocationUpdateReceived = false
//        override fun onNewRawLocation(rawLocation: Location) {
//            viewModel.addCurrentLocation(
//                Point.fromLngLat(
//                    rawLocation.longitude,
//                    rawLocation.latitude
//                )
//            )
//        }
//
//        override fun onNewLocationMatcherResult(locationMatcherResult: LocationMatcherResult) {
//            val enhancedLocation = locationMatcherResult.enhancedLocation
//            navigationLocationProvider.changePosition(
//                location = enhancedLocation,
//                keyPoints = locationMatcherResult.keyPoints
//            )
//            viewportDataSource.onLocationChanged(enhancedLocation)
//            viewportDataSource.evaluate()
//            if (!firstLocationUpdateReceived) {
//                firstLocationUpdateReceived = true
//            }
//        }
//    }
//    private val routeProgressObserver = RouteProgressObserver { routeProgress ->
//        viewportDataSource.onRouteProgressChanged(routeProgress)
//        viewportDataSource.evaluate()
//        val style = mapboxMap.getStyle()
//        if (style != null) {
//            val maneuverArrowResult = routeArrowApi.addUpcomingManeuverArrow(routeProgress)
//            routeArrowView.renderManeuverUpdate(style, maneuverArrowResult)
//        }
//        val maneuvers = maneuverApi.getManeuvers(routeProgress)
//        maneuvers.fold(
//            { error ->
//                Toast.makeText(
//                    requireContext(),
//                    error.errorMessage,
//                    Toast.LENGTH_SHORT
//                ).show()
//            },
//            {
//                binding.maneuverView.visibility = View.VISIBLE
//                binding.maneuverView.renderManeuvers(maneuvers)
//            }
//        )
//        binding.tripProgressView.render(
//            tripProgressApi.getTripProgress(routeProgress)
//        )
//    }
//    private val routesObserver = RoutesObserver { routeUpdateResult ->
//        if (routeUpdateResult.routes.isNotEmpty()) {
//            val routeLines = routeUpdateResult.routes.map { RouteLine(it, null) }
//            routeLineApi.setRoutes(
//                routeLines
//            ) { value ->
//                mapboxMap.getStyle()?.apply {
//                    routeLineView.renderRouteDrawData(this, value)
//                }
//            }
//            viewportDataSource.onRouteChanged(routeUpdateResult.routes.first())
//            viewportDataSource.evaluate()
//        } else {
//            val style = mapboxMap.getStyle()
//            if (style != null) {
//                routeLineApi.clearRouteLine { value ->
//                    routeLineView.renderClearRouteLineValue(
//                        style,
//                        value
//                    )
//                }
//                routeArrowView.render(style, routeArrowApi.clearArrows())
//            }
//            viewportDataSource.clearRouteData()
//            viewportDataSource.evaluate()
//        }
//    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMapBinding =
        FragmentMapBinding::inflate

    override fun setupUI() {
//        mapboxMap = binding.mapView.getMapboxMap()
//
//        binding.mapView.location.apply {
//            locationPuck = LocationPuck2D(
//                bearingImage = ContextCompat.getDrawable(requireContext(), R.drawable.ic_navigation)
//            )
//            setLocationProvider(navigationLocationProvider)
//            enabled = true
//        }
//
//        initMapBox()
//
//        navigationCamera =
//            NavigationCamera(mapboxMap, binding.mapView.camera, viewportDataSource).apply {
//                binding.mapView.camera.addCameraAnimationsLifecycleListener(
//                    NavigationBasicGesturesHandler(this)
//                )
//                registerNavigationCameraStateChangeObserver { navigationCameraState ->
//                    when (navigationCameraState) {
//                        TRANSITION_TO_FOLLOWING,
//                        FOLLOWING -> binding.recenter.visibility = View.INVISIBLE
//                        TRANSITION_TO_OVERVIEW,
//                        OVERVIEW,
//                        IDLE -> binding.recenter.visibility = View.VISIBLE
//                    }
//                }
//            }
//
//        mapboxMap.loadStyleUri(Style.DARK)
//
//        binding.mapView.gestures.addOnMapLongClickListener { point ->
//            findRoute(point, true)
//            true
//        }
//        binding.stop.setOnClickListener {
//            clearRouteAndStopNavigation()
//        }
//        binding.recenter.setOnClickListener {
//            navigationCamera?.requestNavigationCameraToFollowing()
//            binding.routeOverview.showTextAndExtend(BUTTON_ANIMATION_DURATION)
//        }
//        binding.routeOverview.setOnClickListener {
//            navigationCamera?.requestNavigationCameraToOverview()
//            binding.recenter.showTextAndExtend(BUTTON_ANIMATION_DURATION)
//        }
//        binding.soundButton.setOnClickListener {
//            isVoiceInstructionsMuted = !isVoiceInstructionsMuted
//        }
//        binding.soundButton.unmute()
//
//        viewModel.getCurrentLocation()?.let {
//            mapboxMap.setCamera(CameraOptions.Builder().center(it).zoom(16.0).build())
//        }
    }

//    private fun initMapBox() {
//        mapboxNavigation = getNavigator()
//        viewportDataSource = MapboxNavigationViewportDataSource(mapboxMap)
//        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            viewportDataSource.overviewPadding = landscapeOverviewPadding
//        } else {
//            viewportDataSource.overviewPadding = overviewPadding
//        }
//        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            viewportDataSource.followingPadding = landscapeFollowingPadding
//        } else {
//            viewportDataSource.followingPadding = followingPadding
//        }
//        val distanceFormatterOptions =
//            mapboxNavigation.navigationOptions.distanceFormatterOptions
//        maneuverApi = MapboxManeuverApi(MapboxDistanceFormatter(distanceFormatterOptions))
//        tripProgressApi = getTripProgressApi(distanceFormatterOptions)
//
//        speechApi = MapboxSpeechApi(
//            requireContext(),
//            getString(R.string.mapbox_access_token),
//            Locale.US.language
//        )
//        voiceInstructionsPlayer = MapboxVoiceInstructionsPlayer(
//            requireContext(),
//            getString(R.string.mapbox_access_token),
//            Locale.US.language
//        )
//        val mapboxRouteLineOptions = MapboxRouteLineOptions.Builder(requireContext())
//            .withRouteLineBelowLayerId("road-label")
//            .build()
//        routeLineApi = MapboxRouteLineApi(mapboxRouteLineOptions)
//        routeLineView = MapboxRouteLineView(mapboxRouteLineOptions)
//
//        val routeArrowOptions = RouteArrowOptions.Builder(requireContext()).build()
//        routeArrowView = MapboxRouteArrowView(routeArrowOptions)
//
//        mapboxNavigation.apply {
//            if (checkLocationPermission(requireContext())) {
//                startTripSession()
//            }
//            registerRoutesObserver(routesObserver)
//            registerRouteProgressObserver(routeProgressObserver)
//            registerLocationObserver(locationObserver)
//            registerVoiceInstructionsObserver(voiceInstructionsObserver)
//            registerRouteProgressObserver(replayProgressObserver)
//        }
//    }
//
//    private fun getNavigator(): MapboxNavigation =
//        if (MapboxNavigationProvider.isCreated()) {
//            MapboxNavigationProvider.retrieve()
//        } else {
//            MapboxNavigationProvider.create(
//                NavigationOptions.Builder(requireContext())
//                    .accessToken(getString(R.string.mapbox_access_token))
//                    .locationEngine(replayLocationEngine)
//                    .build()
//            )
//        }
//
//    private fun getTripProgressApi(distanceFormatterOptions: DistanceFormatterOptions): MapboxTripProgressApi =
//        MapboxTripProgressApi(
//            TripProgressUpdateFormatter.Builder(requireContext())
//                .distanceRemainingFormatter(
//                    DistanceRemainingFormatter(distanceFormatterOptions)
//                )
//                .timeRemainingFormatter(
//                    TimeRemainingFormatter(requireContext())
//                )
//                .percentRouteTraveledFormatter(
//                    PercentDistanceTraveledFormatter()
//                )
//                .estimatedTimeToArrivalFormatter(
//                    EstimatedTimeToArrivalFormatter(requireContext(), TimeFormat.NONE_SPECIFIED)
//                )
//                .build()
//        )
//
//    override fun onStart() {
//        super.onStart()
//
//        if (checkLocationPermission(requireContext())) {
//            val points = mutableListOf<ReplayEventBase>()
//            viewModel.getCurrentLocation()?.let { current ->
//                points.add(
//                    ReplayRouteMapper.mapToUpdateLocation(
//                        eventTimestamp = 0.0,
//                        point = current
//                    )
//                )
//            }
//            mapboxReplayer.pushEvents(points)
//            mapboxReplayer.playFirstLocation()
//        }
//
//        viewModel.getDestinationLocation()?.let {
//            findRoute(it, false)
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        MapboxNavigationProvider.destroy()
//
//        mapboxReplayer.finish()
//        maneuverApi.cancel()
//        routeLineApi.cancel()
//        routeLineView.cancel()
//        speechApi.cancel()
//        voiceInstructionsPlayer.shutdown()
//
//        mapboxNavigation.unregisterRoutesObserver(routesObserver)
//        mapboxNavigation.unregisterRouteProgressObserver(routeProgressObserver)
//        mapboxNavigation.unregisterLocationObserver(locationObserver)
//        mapboxNavigation.unregisterVoiceInstructionsObserver(voiceInstructionsObserver)
//        mapboxNavigation.unregisterRouteProgressObserver(replayProgressObserver)
//    }
//
//    private fun findRoute(destination: Point, isNewPoint: Boolean) {
//        viewModel.addDestinationLocation(destination)
//        val originPoint = viewModel.getCurrentLocation() ?: return
//        val originLocation = Location("").apply {
//            latitude = originPoint.latitude()
//            longitude = originPoint.longitude()
//        }
//
//        mapboxNavigation.requestRoutes(
//            RouteOptions.builder()
//                .applyDefaultNavigationOptions()
//                .applyLanguageAndVoiceUnitOptions(requireContext())
//                .coordinatesList(listOf(originPoint, destination))
//                .bearingsList(
//                    listOf(
//                        Bearing.builder()
//                            .angle(originLocation.bearing.toDouble())
//                            .degrees(45.0)
//                            .build(),
//                        null
//                    )
//                )
//                .layersList(listOf(mapboxNavigation.getZLevel(), null))
//                .build(),
//            object : RouterCallback {
//                override fun onRoutesReady(
//                    routes: List<DirectionsRoute>,
//                    routerOrigin: RouterOrigin
//                ) {
//                    setRouteAndStartNavigation(routes, isNewPoint)
//                }
//
//                override fun onFailure(
//                    reasons: List<RouterFailure>,
//                    routeOptions: RouteOptions
//                ) {}
//
//                override fun onCanceled(
//                    routeOptions: RouteOptions,
//                    routerOrigin: RouterOrigin
//                ) {}
//            }
//        )
//    }
//
//    private fun setRouteAndStartNavigation(routes: List<DirectionsRoute>, isNewPoint: Boolean) {
//        mapboxNavigation.setRoutes(routes)
//        // startSimulation(routes.first())
//        binding.soundButton.visibility = View.VISIBLE
//        binding.routeOverview.visibility = View.VISIBLE
//        binding.tripProgressCard.visibility = View.VISIBLE
//        navigationCamera?.requestNavigationCameraToOverview()
//
//        if (isNewPoint && routes.isNotEmpty()) {
//            routes.first().legs()?.first()?.summary()?.let {
//                viewModel.addAddress(it)
//            }
//        }
//    }
//
//    private fun clearRouteAndStopNavigation() {
//        viewModel.removeDestinationLocation()
//        mapboxNavigation.setRoutes(listOf())
//        mapboxReplayer.stop()
//        binding.soundButton.visibility = View.INVISIBLE
//        binding.maneuverView.visibility = View.INVISIBLE
//        binding.routeOverview.visibility = View.INVISIBLE
//        binding.tripProgressCard.visibility = View.INVISIBLE
//    }

//    private fun startSimulation(route: DirectionsRoute) {
//        mapboxReplayer.run {
//            stop()
//            clearEvents()
//            val replayEvents = ReplayRouteMapper().mapDirectionsRouteGeometry(route)
//            pushEvents(replayEvents)
//            seekTo(replayEvents.first())
//            play()
//        }
//    }
}
