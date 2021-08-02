# ICARUS Usage Analytics API Module
## Overview
The ICARUS Usage Analytics API Module is a containerized service that provides the stored aggregated utilization data to the platform's users. 

## Install
The whole service runs in the container, and the user needs first to compile the maven project and then run the appropriate docker commands.

### Maven build
```
mvn clean install package -Denvironment=prod -DskipTests
```

### Docker build
```
docker build -t usage_analytics_api .
```

### Docker run
```
docker run -d -p 8095:8095 --rm --name usage_analytics_api usage_analytics_api
```

### Docker stop
```
docker stop usage_analytics_api
```

## Available REST API Requests
Each request requires a token that will verify if (i) the user is exists (ii) if the user is admin.
### GET Requests

#### Get all general platform statistics
```
localhost:8095/api/v1/usage-analytics/admin/count-stats
```

#### Get all categories and the number of datasets they are tagged in that category
```
localhost:8095/api/v1/usage-analytics/admin/data-assets-per-category
```

#### Get a timeline for a specific type of event
```
localhost:8095/api/v1/usage-analytics/admin/timeline?{eventType}
```

#### Get a timeline of the number of active users on the platform
```
localhost:8095/api/v1/usage-analytics/admin/timeline-active-users
```

#### Get all data assets per velocity
```
localhost:8095/api/v1/usage-analytics/admin/data-asset-per-velocity
```

#### Get all data assets per frequency
```
localhost:8095/api/v1/usage-analytics/admin/data-asset-per-frequency
```

#### Get all data assets per calculation scheme
```
localhost:8095/api/v1/usage-analytics/admin/data-asset-per-calculation-scheme
```

#### Get all data assets events per user
```
localhost:8095/api/v1/usage-analytics/admin/data-asset-events
```

#### Get specific data assets events per user in a timeline format
```
localhost:8095/api/v1/usage-analytics/admin/timeline-data-asset-events?{eventType}
```

#### Get all data assets events per user in a timeline format
```
localhost:8095/api/v1/usage-analytics/admin/timeline-all-data-asset-events
```

#### Get all general data asset events per user
```
localhost:8095/api/v1/usage-analytics/admin/user-data-asset-events
```

#### Get all categories and the number of organizations that have set it as a preference
```
localhost:8095/api/v1/usage-analytics/admin/category-preferences
```

#### Get all organizations per country
```
localhost:8095/api/v1/usage-analytics/admin/organization-per-country
```

#### Get the 5 public most popular data assets on the platform
```
localhost:8095/api/v1/usage-analytics/admin/popular-category-datasets
```